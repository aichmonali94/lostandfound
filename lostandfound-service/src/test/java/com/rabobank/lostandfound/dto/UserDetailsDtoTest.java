package com.rabobank.lostandfound.dto;

import com.rabobank.lostandfound.model.LostItem;
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
class UserDetailsDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenValidUserDetailsDto_whenValidated_thenNoConstraintViolations() {
        UserDetailsDto userDetails = new UserDetailsDto();
        userDetails.setUserId(1001L);
        userDetails.setUserName("Jane Doe");
        userDetails.setItemName("Laptop");
        userDetails.setItemId(1L);
        userDetails.setQuantity(1);
        userDetails.setClaimedItems(new HashSet<>());

        Set violations = validator.validate(userDetails);
        assertTrue(violations.isEmpty(), "There should be no constraint violations");
    }

    @Test
    void givenNullUserId_whenValidated_thenConstraintViolation() {
        UserDetailsDto userDetails = new UserDetailsDto();
        userDetails.setUserId(null);
        userDetails.setUserName("Jane Doe");
        userDetails.setItemName("Laptop");
        userDetails.setItemId(1L);
        userDetails.setQuantity(1);
        userDetails.setClaimedItems(new HashSet<>());

        Set violations = validator.validate(userDetails);
        assertEquals(1, violations.size(), "There should be one constraint violation");
    }
}

