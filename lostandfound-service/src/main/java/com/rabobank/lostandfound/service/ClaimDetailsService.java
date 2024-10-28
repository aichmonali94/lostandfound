package com.rabobank.lostandfound.service;

import com.rabobank.lostandfound.dto.ClaimDetailsDto;
import com.rabobank.lostandfound.dto.LostItemDto;

public interface ClaimDetailsService {

    LostItemDto claimRequest(ClaimDetailsDto claimDto);
}
