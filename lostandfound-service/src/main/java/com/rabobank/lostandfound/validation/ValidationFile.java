package com.rabobank.lostandfound.validation;

import com.rabobank.lostandfound.dto.ClaimDetailsDto;
import com.rabobank.lostandfound.exception.ClaimException;
import com.rabobank.lostandfound.model.LostItem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidationFile {

    public boolean validateClaimRequest(LostItem lostItem, ClaimDetailsDto claimDto){

        if (claimDto.getQuantity() <= 0) {
            throw new ClaimException("Quantity must be greater than zero.",607, LocalDateTime.now());
        }

        if (lostItem.getQuantity() < claimDto.getQuantity()) {
            throw new ClaimException("Not enough quantity available to claim.", 608, LocalDateTime.now());
        }

        return true;
    }
}
