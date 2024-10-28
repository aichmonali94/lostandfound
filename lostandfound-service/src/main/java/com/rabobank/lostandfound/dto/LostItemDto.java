package com.rabobank.lostandfound.dto;

import com.rabobank.lostandfound.model.UserDetails;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LostItemDto{

    @NotNull
    private Long lostItemEntryId;

    @NotEmpty
    private String itemName;

    @NotNull
    private Integer quantity;

    @NotEmpty
    private String place;

    private Set<UserDetails> claimedByUsers;
}
