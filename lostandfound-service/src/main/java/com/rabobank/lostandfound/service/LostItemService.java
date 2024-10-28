package com.rabobank.lostandfound.service;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.model.LostItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface LostItemService {

    Set<LostItemDto> getAllLostItemsWithUserDetails();

    Set<LostItemDto> getAllLostItems();

    //Set<LostItem> getClaimedItems(String lostItemId);
}
