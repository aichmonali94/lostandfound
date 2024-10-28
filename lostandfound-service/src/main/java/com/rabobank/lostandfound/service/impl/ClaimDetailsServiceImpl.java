package com.rabobank.lostandfound.service.impl;

import com.rabobank.lostandfound.dto.ClaimDetailsDto;
import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.exception.ClaimException;
import com.rabobank.lostandfound.exception.NoLostItemsFoundException;
import com.rabobank.lostandfound.feign.UserService;
import com.rabobank.lostandfound.mapper.LostItemMapper;
import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.model.UserDetails;
import com.rabobank.lostandfound.repository.LostItemRepository;
import com.rabobank.lostandfound.repository.UserDetailsRepository;
import com.rabobank.lostandfound.service.ClaimDetailsService;
import com.rabobank.lostandfound.validation.ValidationFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClaimDetailsServiceImpl implements ClaimDetailsService {

    @Autowired
    LostItemMapper lostItemMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsRepository userRepo;

    @Autowired
    LostItemRepository lostItemRepo;

    @Autowired
    ValidationFile validate;

    @Override
    public LostItemDto claimRequest(ClaimDetailsDto claimDto) {

        LostItemDto lostItemDto = new LostItemDto();
        LostItem lostItem = lostItemRepo.findById(claimDto.getItemId())
                .orElseThrow(() -> new NoLostItemsFoundException("Lost item with ID " + claimDto.getItemId() + " not found", 606, LocalDateTime.now()));

        boolean isValid = validate.validateClaimRequest(lostItem, claimDto);
        if(!isValid)
            throw new ClaimException("Claim request is not valid.", 609, LocalDateTime.now());

        UserDetails userDetails = new UserDetails();
        Set<UserDetails> user = new HashSet<>();
        userDetails.setUserId(claimDto.getUserId());
        userDetails.setUserName(userService.getUserNameById(claimDto.getUserId()));
        userDetails.setItemName(lostItem.getItemName());
        userDetails.setItemId(claimDto.getItemId());
        userDetails.setQuantity(claimDto.getQuantity());
        user.add(userDetails);
        userRepo.save(userDetails);

        lostItem.setQuantity(lostItem.getQuantity() - claimDto.getQuantity());
        lostItemDto.setClaimedByUsers(user);
        lostItemRepo.save(lostItem);

        lostItemDto.setLostItemEntryId(lostItem.getLostItemEntryId());
        lostItemDto.setItemName(lostItem.getItemName());
        lostItemDto.setQuantity(lostItem.getQuantity());
        lostItemDto.setPlace(lostItem.getPlace());

        return lostItemDto;
    }
}
