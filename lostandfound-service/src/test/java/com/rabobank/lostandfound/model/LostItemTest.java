package com.rabobank.lostandfound.model;

import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.model.UserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LostItemTest {

    @Test
    public void testLostItemCreation() {

        LostItem lostItem = LostItem.builder()
                .itemName("Laptop")
                .quantity(5)
                .place("Office")
                .claimedByUsers(new HashSet<>())
                .build();


        Long itemId = lostItem.getLostItemEntryId();

        assertThat(lostItem.getItemName()).isEqualTo("Laptop");
        assertThat(lostItem.getQuantity()).isEqualTo(5);
        assertThat(lostItem.getPlace()).isEqualTo("Office");
        assertThat(lostItem.getClaimedByUsers()).isNotNull();
        assertThat(itemId).isNull();
    }
}

