package com.rabobank.lostandfound.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

    @NotNull
    @Id
    private Long userId;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String itemName;

    @NotNull
    private Long itemId;

    @NotNull
    private int quantity;

    @ManyToMany(mappedBy = "claimedByUsers")
    @JsonIgnore
    private Set<LostItem> claimedItems;
}
