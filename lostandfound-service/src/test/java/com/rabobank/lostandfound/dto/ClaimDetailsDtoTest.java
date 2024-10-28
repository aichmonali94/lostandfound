package com.rabobank.lostandfound.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClaimDetailsDtoTest {

        private Validator validator;

        @BeforeEach
        void setUp() {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }

        @Test
        void givenValidClaimDetailsDto_whenValidated_thenNoConstraintViolations() {
            ClaimDetailsDto claimDetails = new ClaimDetailsDto();
            claimDetails.setUserId(1001L);
            claimDetails.setUserName("John Doe");
            claimDetails.setItemId(1L);
            claimDetails.setItemName("Laptop");
            claimDetails.setQuantity(2);

            Set violations = validator.validate(claimDetails);
            assertTrue(violations.isEmpty(), "There should be no constraint violations");
        }

    }
