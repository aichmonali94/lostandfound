package com.rabobank.lostandfound.validation;

import com.rabobank.lostandfound.dto.ClaimDetailsDto;
import com.rabobank.lostandfound.exception.ClaimException;
import com.rabobank.lostandfound.model.LostItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidationFileTest {

    private final ValidationFile validationFile = new ValidationFile();

    @Test
    public void testValidateClaimRequest_QuantityLessThanOrEqualToZero() {
        LostItem lostItem = new LostItem();
        lostItem.setQuantity(5);

        ClaimDetailsDto claimDto = new ClaimDetailsDto();
        claimDto.setQuantity(0);

        ClaimException exception = assertThrows(ClaimException.class, () -> {
            validationFile.validateClaimRequest(lostItem, claimDto);
        });

        assertEquals("Quantity must be greater than zero.", exception.getMessage());
        assertEquals(607, exception.getStatus());
    }

    @Test
    public void testValidateClaimRequest_InsufficientQuantity() {
        LostItem lostItem = new LostItem();
        lostItem.setQuantity(3);

        ClaimDetailsDto claimDto = new ClaimDetailsDto();
        claimDto.setQuantity(5);

        ClaimException exception = assertThrows(ClaimException.class, () -> {
            validationFile.validateClaimRequest(lostItem, claimDto);
        });

        assertEquals("Not enough quantity available to claim.", exception.getMessage());
        assertEquals(608, exception.getStatus());
    }

    @Test
    public void testValidateClaimRequest_ValidClaim() {
        LostItem lostItem = new LostItem();
        lostItem.setQuantity(5);

        ClaimDetailsDto claimDto = new ClaimDetailsDto();
        claimDto.setQuantity(3);

        boolean result = validationFile.validateClaimRequest(lostItem, claimDto);
        assertTrue(result);
    }

}
