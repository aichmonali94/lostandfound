package com.rabobank.lostandfound.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "LOST_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostItemEntryId;

    @NotEmpty
    private String itemName;

    @NotNull
    private Integer quantity;

    @NotEmpty
    private String place;

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(name = "USER_LOSTITEM" ,
            joinColumns = {
                    @JoinColumn( name = "lostitem_id" , referencedColumnName = "lostItemEntryId"),
            },
            inverseJoinColumns = {
                    @JoinColumn ( name = "user_id", referencedColumnName = "userId")
            })
    private Set<UserDetails> claimedByUsers;
}
