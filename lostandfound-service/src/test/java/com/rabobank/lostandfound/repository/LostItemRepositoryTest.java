package com.rabobank.lostandfound.repository;

import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.service.LostItemService;
import com.rabobank.lostandfound.service.impl.LostItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LostItemRepositoryTest {

    @Mock
    private LostItemRepository lostItemRepository;

    @InjectMocks
    private LostItemServiceImpl lostItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAndFindLostItem() {
        LostItem lostItem = new LostItem();
        lostItem.setLostItemEntryId(1001L);
        lostItem.setItemName("Wallet");
        lostItem.setQuantity(1);
        lostItem.setPlace("Café");

        // Mocking behavior
        when(lostItemRepository.save(lostItem)).thenReturn(lostItem);
        when(lostItemRepository.findById(1001L)).thenReturn(Optional.of(lostItem));

        // Act
        LostItem savedItem = lostItemRepository.save(lostItem);
        LostItem foundItem = lostItemRepository.findById(savedItem.getLostItemEntryId()).orElse(null);

        // Assert
        assertThat(foundItem).isNotNull();
        assertThat(foundItem.getItemName()).isEqualTo("Wallet");
        assertThat(foundItem.getQuantity()).isEqualTo(1);
        assertThat(foundItem.getPlace()).isEqualTo("Café");
    }

    @Test
    public void testFindAllLostItems() {
        LostItem lostItem1 = new LostItem();
        lostItem1.setLostItemEntryId(1002L);
        lostItem1.setItemName("Keys");
        lostItem1.setQuantity(1);
        lostItem1.setPlace("Park");

        LostItem lostItem2 = new LostItem();
        lostItem2.setLostItemEntryId(1003L);
        lostItem2.setItemName("Bag");
        lostItem2.setQuantity(2);
        lostItem2.setPlace("Library");

        // Mocking behavior
        when(lostItemRepository.findAll()).thenReturn(Arrays.asList(lostItem1, lostItem2));

        // Act
        List<LostItem> lostItems = lostItemRepository.findAll();

        // Assert
        assertThat(lostItems).hasSize(2);
        assertThat(lostItems).extracting("itemName").contains("Keys", "Bag");
    }

    @Test
    public void testDeleteLostItem() {
        LostItem lostItem = new LostItem();
        lostItem.setLostItemEntryId(1002L);
        lostItem.setItemName("Sunglasses");
        lostItem.setQuantity(1);
        lostItem.setPlace("Beach");

        // Mocking behavior
        when(lostItemRepository.save(lostItem)).thenReturn(lostItem);
        doNothing().when(lostItemRepository).deleteById(1002L);

        // Act
        lostItemRepository.save(lostItem);
        lostItemRepository.deleteById(lostItem.getLostItemEntryId());

        // Verify that deleteById was called
        verify(lostItemRepository).deleteById(lostItem.getLostItemEntryId());
        assertThat(lostItemRepository.findById(lostItem.getLostItemEntryId())).isEmpty();
    }
}

