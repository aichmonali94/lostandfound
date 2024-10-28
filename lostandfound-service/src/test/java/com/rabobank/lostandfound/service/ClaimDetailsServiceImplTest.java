package com.rabobank.lostandfound.service;

import com.rabobank.lostandfound.dto.ClaimDetailsDto;
import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.exception.ClaimException;
import com.rabobank.lostandfound.exception.NoLostItemsFoundException;
import com.rabobank.lostandfound.feign.UserService;
import com.rabobank.lostandfound.mapper.LostItemMapper;
import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.model.UserDetails;
import com.rabobank.lostandfound.repository.LostItemRepository;
import com.rabobank.lostandfound.repository.UserDetailsRepository;
import com.rabobank.lostandfound.service.impl.ClaimDetailsServiceImpl;
import com.rabobank.lostandfound.validation.ValidationFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClaimDetailsServiceImplTest {

    @InjectMocks
    private ClaimDetailsServiceImpl claimDetailsService;

    @Mock
    private LostItemRepository lostItemRepo;

    @Mock
    private UserDetailsRepository userRepo;

    @Mock
    private ValidationFile validate;

    @Mock
    private UserService userService;

    @Mock
    private LostItemMapper lostItemMapper;

    private ClaimDetailsDto claimDto;
    private LostItem lostItem;
    private LostItemDto lostItemDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        claimDto = new ClaimDetailsDto();
        claimDto.setUserId(1L);
        claimDto.setItemId(2L);
        claimDto.setQuantity(1);

        lostItem = new LostItem();
        lostItem.setLostItemEntryId(2L);
        lostItem.setItemName("Test Item");
        lostItem.setQuantity(5);
        lostItem.setPlace("Test Place");

        lostItemDto = new LostItemDto();
    }

    @Test
    public void claimRequest_Success() {
        when(lostItemRepo.findById(claimDto.getItemId())).thenReturn(Optional.of(lostItem));
        when(validate.validateClaimRequest(lostItem, claimDto)).thenReturn(true);
        when(userService.getUserNameById(claimDto.getUserId())).thenReturn("Test User");

        LostItemDto result = claimDetailsService.claimRequest(claimDto);

        assertEquals(lostItem.getLostItemEntryId(), result.getLostItemEntryId());
        assertEquals(lostItem.getItemName(), result.getItemName());
        assertEquals(4, result.getQuantity());
        assertEquals(lostItem.getPlace(), result.getPlace());

        verify(userRepo).save(any(UserDetails.class));
        verify(lostItemRepo).save(lostItem);
    }

    @Test
    public void claimRequest_InvalidClaimRequest() {
        when(lostItemRepo.findById(claimDto.getItemId())).thenReturn(Optional.of(lostItem));
        when(validate.validateClaimRequest(lostItem, claimDto)).thenReturn(false);

        ClaimException exception = assertThrows(ClaimException.class, () -> {
            claimDetailsService.claimRequest(claimDto);
        });

        assertEquals("Claim request is not valid.", exception.getMessage());
    }

    @Test
    public void claimRequest_LostItemNotFound() {
        when(lostItemRepo.findById(claimDto.getItemId())).thenReturn(Optional.empty());

        NoLostItemsFoundException exception = assertThrows(NoLostItemsFoundException.class, () -> {
            claimDetailsService.claimRequest(claimDto);
        });

        assertEquals("Lost item with ID " + claimDto.getItemId() + " not found", exception.getMessage());
    }

}
