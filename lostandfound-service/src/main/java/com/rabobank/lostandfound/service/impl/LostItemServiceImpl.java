package com.rabobank.lostandfound.service.impl;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.exception.NoLostItemsFoundException;
import com.rabobank.lostandfound.mapper.LostItemMapper;
import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.model.UserDetails;
import com.rabobank.lostandfound.repository.LostItemRepository;
import com.rabobank.lostandfound.repository.UserDetailsRepository;
import com.rabobank.lostandfound.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LostItemServiceImpl implements LostItemService {

    @Autowired
    LostItemRepository lostItemRepository;

    @Autowired
    UserDetailsRepository userRepo;

    @Autowired
    private LostItemMapper lostItemMapper;


    @Override
    public Set<LostItemDto> getAllLostItemsWithUserDetails() {

        List<LostItem> lostItems = lostItemRepository.findAll();
        if (lostItems == null || lostItems.isEmpty()) {
            throw new NoLostItemsFoundException("No lost items found.", 605, LocalDateTime.now());
        }

        return lostItems.stream()
                .map(lostItem -> {
                    LostItemDto lostItemDto = new LostItemDto();
                    Set<UserDetails> claimedByUsers = userRepo.findUserByItemId(lostItem.getLostItemEntryId());
                    if (claimedByUsers != null) {
                        lostItemDto.setClaimedByUsers(claimedByUsers);
                    }
                    lostItemDto.setLostItemEntryId(lostItem.getLostItemEntryId());
                    lostItemDto.setItemName(lostItem.getItemName());
                    lostItemDto.setQuantity(lostItem.getQuantity());
                    lostItemDto.setPlace(lostItem.getPlace());
                    return lostItemDto;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LostItemDto> getAllLostItems() {
        List<LostItem> lostItems = lostItemRepository.findAll();
        if (lostItems == null || lostItems.isEmpty()) {
            throw new NoLostItemsFoundException("No lost items found.", 605, LocalDateTime.now());
        }

        return lostItems.stream()
                .map(lostItem -> lostItemMapper.mapToLostItemDto(lostItem))
                .collect(Collectors.toSet());
    }
}
