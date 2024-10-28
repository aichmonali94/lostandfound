package com.rabobank.lostandfound.controller;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.model.UserDetails;
import com.rabobank.lostandfound.service.LostItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class LostItemControllerTest {

    @InjectMocks
    private LostItemController lostItemController;

    @Mock
    private LostItemService lostItemService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(lostItemController).build();
    }

    @Test
    public void getLostItemsWithUserDtls_Success() throws Exception {
        Set<LostItemDto> expectedItems = new HashSet<>();
        Set<UserDetails> claimedUser = new HashSet<>();
        expectedItems.add(new LostItemDto(1L,"Laptop",1,"taxi",claimedUser));

        when(lostItemService.getAllLostItemsWithUserDetails()).thenReturn(expectedItems);

        mockMvc.perform(get("/api/v1/lostandfound/lostitem/getLostItemsWithUserDtls")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(lostItemService).getAllLostItemsWithUserDetails();
    }

    @Test
    public void getAllLostItems_Success() throws Exception {
        Set<LostItemDto> expectedItems = new HashSet<>();
        expectedItems.add(new LostItemDto());

        when(lostItemService.getAllLostItems()).thenReturn(expectedItems);

        mockMvc.perform(get("/api/v1/lostandfound/lostitem/getAllLostItems")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(lostItemService).getAllLostItems();
    }
}
