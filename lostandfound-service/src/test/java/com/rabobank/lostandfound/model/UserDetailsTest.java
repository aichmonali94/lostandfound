package com.rabobank.lostandfound.model;

import com.rabobank.lostandfound.model.UserDetails;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDetailsTest {

    private final Validator validator;

    public UserDetailsTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUserDetails() {

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1L);
        userDetails.setUserName("John Doe");
        userDetails.setItemName("Laptop");
        userDetails.setItemId(1001L);
        userDetails.setQuantity(2);

        Set<ConstraintViolation<UserDetails>> violations = validator.validate(userDetails);

        assertThat(violations).isEmpty();
    }

    @Test
    public void testUserDetailsWithEmptyUserName() {

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1L);
        userDetails.setUserName("");
        userDetails.setItemName("Laptop");
        userDetails.setItemId(1001L);
        userDetails.setQuantity(2);

        Set<ConstraintViolation<UserDetails>> violations = validator.validate(userDetails);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must not be empty");
    }

    @Test
    public void testUserDetailsWithNullItemId() {

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1L);
        userDetails.setUserName("John Doe");
        userDetails.setItemName("Laptop");
        userDetails.setItemId(null);
        userDetails.setQuantity(2);

        Set<ConstraintViolation<UserDetails>> violations = validator.validate(userDetails);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must not be null");
    }
}

