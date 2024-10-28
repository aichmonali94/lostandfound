package com.rabobank.lostandfound.dto;

import com.rabobank.lostandfound.model.LostItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
public class UserDetailsDto {

    @NotNull
    private Long userId;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String itemName;

    @NotNull
    private Long itemId;

    @NotNull
    private int quantity;

    private Set<LostItem> claimedItems;
}
