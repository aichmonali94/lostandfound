package com.rabobank.lostandfound.controller;

import com.rabobank.lostandfound.dto.ClaimDetailsDto;
import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.service.ClaimDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lostandfound/claimdetails")
public class ClaimDetailsController {

    @Autowired
    private ClaimDetailsService claimService;

    @Operation(summary = "Claim LostItem Data and save", description = "A REST API user endpoint for users to claim the \n" +
            "lost item.  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "500", description ="Internal Server Error.")
    })
    @PutMapping(value="/user/claimByItem", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<LostItemDto> claimRequest(
            @Valid @RequestParam(required = true) Long userId,
            @Valid @RequestParam(required = true) Long itemId,
            @Valid @RequestParam(required = true) Integer quantity){

        ClaimDetailsDto claimDto = new ClaimDetailsDto();
        claimDto.setUserId(userId);
        claimDto.setItemId(itemId);
        claimDto.setQuantity(quantity);
        return new ResponseEntity<>(claimService.claimRequest(claimDto), HttpStatus.ACCEPTED);
    }

}
