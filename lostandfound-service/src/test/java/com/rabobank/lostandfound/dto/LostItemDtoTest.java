package com.rabobank.lostandfound.dto;

import com.rabobank.lostandfound.model.UserDetails;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LostItemDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenValidLostItemDto_whenValidated_thenNoConstraintViolations() {
        LostItemDto lostItem = new LostItemDto();
        lostItem.setLostItemEntryId(1L);
        lostItem.setItemName("Laptop");
        lostItem.setQuantity(1);
        lostItem.setPlace("Taxi");
        lostItem.setClaimedByUsers(new HashSet<>());

        Set violations = validator.validate(lostItem);
        assertTrue(violations.isEmpty(), "There should be no constraint violations");
    }

    @Test
    void givenNullLostItemEntryId_whenValidated_thenConstraintViolation() {
        LostItemDto lostItem = new LostItemDto();
        lostItem.setLostItemEntryId(null);
        lostItem.setItemName("Laptop");
        lostItem.setQuantity(1);
        lostItem.setPlace("Taxi");
        lostItem.setClaimedByUsers(new HashSet<>());

        Set violations = validator.validate(lostItem);
        assertEquals(1, violations.size(), "There should be one constraint violation");

    }
}

