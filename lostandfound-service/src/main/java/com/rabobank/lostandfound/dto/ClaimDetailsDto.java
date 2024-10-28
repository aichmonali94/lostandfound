package com.rabobank.lostandfound.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rabobank.lostandfound.model.LostItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class ClaimDetailsDto {

    @NotNull(message = "User ID must not be null")
    private Long userId;

    @NotEmpty(message = "User name must not be empty")
    private String userName;

    @NotNull(message = "Item ID must not be null")
    private Long itemId;

    @NotEmpty(message = "Item name must not be empty")
    private String itemName;

    @NotNull(message = "Quantity must not be null")
    private Integer quantity;

}
