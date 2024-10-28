package com.rabobank.lostandfound.repository;

import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.model.UserDetails;
import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.model.UserDetails;
import com.rabobank.lostandfound.repository.UserDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserDetailsRepositoryTest {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private LostItemRepository lostItemRepository;

    private LostItem lostItem;

    @BeforeEach
    public void setUp() {

        lostItem = new LostItem();
        lostItem.setLostItemEntryId(1L);
        lostItem.setItemName("Laptop");
        lostItem.setQuantity(1);
        lostItem.setPlace("Office");
        lostItemRepository.save(lostItem);

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1001L);
        userDetails.setUserName("User1001");
        userDetails.setItemName(lostItem.getItemName());
        userDetails.setItemId(lostItem.getLostItemEntryId());
        userDetailsRepository.save(userDetails);
    }

    @Test
    public void testFindUserByItemId() {

        Set<UserDetails> users = userDetailsRepository.findUserByItemId(lostItem.getLostItemEntryId());

        assertThat(users).isNotEmpty();
        assertThat(users).hasSize(1);
        assertThat(users.iterator().next().getUserName()).isEqualTo("User1001");
    }

    @Test
    public void testFindUserByNonExistingItemId() {

        Set<UserDetails> users = userDetailsRepository.findUserByItemId(9999L);
        assertThat(users).isEmpty();
    }
}

