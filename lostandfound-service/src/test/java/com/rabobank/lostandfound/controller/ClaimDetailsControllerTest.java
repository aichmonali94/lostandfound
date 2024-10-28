package com.rabobank.lostandfound.controller;

import com.rabobank.lostandfound.dto.ClaimDetailsDto;
import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.exception.ClaimException;
import com.rabobank.lostandfound.service.ClaimDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ClaimDetailsControllerTest {

    @InjectMocks
    private ClaimDetailsController claimDetailsController;

    @Mock
    private ClaimDetailsService claimService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(claimDetailsController).build();
    }

    @Test
    public void claimRequest_Success() throws Exception {
        Long userId = 1L;
        Long itemId = 2L;
        Integer quantity = 1;

        LostItemDto expectedItem = new LostItemDto();

        when(claimService.claimRequest(any(ClaimDetailsDto.class))).thenReturn(expectedItem);

        mockMvc.perform(put("/api/v1/lostandfound/claimdetails/claimByItem")
                        .param("userId", userId.toString())
                        .param("itemId", itemId.toString())
                        .param("quantity", quantity.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lostItemEntryId").value(expectedItem.getLostItemEntryId()))
                .andExpect(jsonPath("$.itemName").value(expectedItem.getItemName()))
                .andExpect(jsonPath("$.quantity").value(expectedItem.getQuantity()));

        verify(claimService).claimRequest(any(ClaimDetailsDto.class));
    }

    @Test
    public void claimRequest_InvalidUserId() throws Exception {
        String invalidUserId = "invalidUser"; // Invalid user ID
        Long itemId = 2L;
        Integer quantity = 1;

        mockMvc.perform(put("/api/v1/lostandfound/claimdetails/claimByItem")
                        .param("userId", invalidUserId)
                        .param("itemId", itemId.toString())
                        .param("quantity", quantity.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(claimService, never()).claimRequest(any());
    }

    @Test
    public void claimRequest_InvalidItemId() throws Exception {
        Long userId = 1L;
        String invalidItemId = "invalidItemId"; // Invalid item ID
        Integer quantity = 1;

        mockMvc.perform(put("/api/v1/lostandfound/claimdetails/claimByItem")
                        .param("userId", userId.toString())
                        .param("itemId", invalidItemId)
                        .param("quantity", quantity.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(claimService, never()).claimRequest(any());
    }

    @Test
    public void claimRequest_InvalidQuantity() throws Exception {
        Long userId = 1L;
        Long itemId = 2L;
        Integer invalidQuantity = null; // Invalid quantity

        mockMvc.perform(put("/api/v1/lostandfound/claimdetails/claimByItem")
                        .param("userId", userId.toString())
                        .param("itemId", itemId.toString())
                        .param("quantity", String.valueOf(invalidQuantity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(claimService, never()).claimRequest(any());
    }

}
