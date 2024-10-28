package com.rabobank.lostandfound.service;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.exception.NoLostItemsFoundException;
import com.rabobank.lostandfound.mapper.LostItemMapper;
import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.model.UserDetails;
import com.rabobank.lostandfound.repository.LostItemRepository;
import com.rabobank.lostandfound.repository.UserDetailsRepository;
import com.rabobank.lostandfound.service.impl.LostItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LostItemServiceImplTest {

    @InjectMocks
    private LostItemServiceImpl lostItemService;

    @Mock
    private LostItemRepository lostItemRepository;

    @Mock
    private UserDetailsRepository userRepo;

    @Mock
    private LostItemMapper lostItemMapper;

    private LostItem lostItem1;
    private LostItem lostItem2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        lostItem1 = new LostItem();
        lostItem1.setLostItemEntryId(1L);
        lostItem1.setItemName("Item 1");
        lostItem1.setQuantity(5);
        lostItem1.setPlace("Place 1");

        lostItem2 = new LostItem();
        lostItem2.setLostItemEntryId(2L);
        lostItem2.setItemName("Item 2");
        lostItem2.setQuantity(3);
        lostItem2.setPlace("Place 2");
    }

    @Test
    public void getAllLostItemsWithUserDetails_Success() {
        when(lostItemRepository.findAll()).thenReturn(Arrays.asList(lostItem1, lostItem2));

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1L);
        userDetails.setUserName("User 1");

        when(userRepo.findUserByItemId(lostItem1.getLostItemEntryId())).thenReturn(new HashSet<>(Arrays.asList(userDetails)));
        when(userRepo.findUserByItemId(lostItem2.getLostItemEntryId())).thenReturn(new HashSet<>());

        Set<LostItemDto> result = lostItemService.getAllLostItemsWithUserDetails();

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(dto -> dto.getItemName().equals("Item 1") && dto.getClaimedByUsers().size() == 1));
        assertTrue(result.stream().anyMatch(dto -> dto.getItemName().equals("Item 2") && dto.getClaimedByUsers().isEmpty()));
    }

    @Test
    public void getAllLostItemsWithUserDetails_NoLostItemsFound() {
        when(lostItemRepository.findAll()).thenReturn(List.of());

        NoLostItemsFoundException exception = assertThrows(NoLostItemsFoundException.class, () -> {
            lostItemService.getAllLostItemsWithUserDetails();
        });

        assertEquals("No lost items found.", exception.getMessage());
        assertEquals(605, exception.getStatus());
    }

    @Test
    public void getAllLostItems_Success() {
        when(lostItemRepository.findAll()).thenReturn(Arrays.asList(lostItem1, lostItem2));
        when(lostItemMapper.mapToLostItemDto(lostItem1)).thenReturn(new LostItemDto());
        when(lostItemMapper.mapToLostItemDto(lostItem2)).thenReturn(new LostItemDto());

        Set<LostItemDto> result = lostItemService.getAllLostItems();

        assertEquals(1, result.size());
        verify(lostItemMapper, times(2)).mapToLostItemDto(any(LostItem.class));
    }

    @Test
    public void getAllLostItems_NoLostItemsFound() {
        when(lostItemRepository.findAll()).thenReturn(List.of());

        NoLostItemsFoundException exception = assertThrows(NoLostItemsFoundException.class, () -> {
            lostItemService.getAllLostItems();
        });

        assertEquals("No lost items found.", exception.getMessage());
        assertEquals(605, exception.getStatus());
    }

}
