package com.rabobank.lostandfound.repository;

import com.rabobank.lostandfound.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query("SELECT u FROM UserDetails u LEFT JOIN LostItem li on li.lostItemEntryId = u.itemId WHERE u.itemId = :itemId")
    Set<UserDetails> findUserByItemId(@Param("itemId") Long itemId);
}
