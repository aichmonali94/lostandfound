package com.rabobank.lostandfound.controller;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.service.LostItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/lostandfound/lostitem")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    @Operation(summary = "Retrieved Lost Items claimed by users", description = "A REST API Admin endpoint to read all \n" +
            "the Lost items and Users (userId and name) associated with that.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description ="Internal Server Error."),
            @ApiResponse(responseCode = "403", description="Forbidden for other user.")
    })
    @GetMapping("/admin/getLostItemsWithUserDtls")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<LostItemDto>> getLostItemsWithUserDtls() {
        return new ResponseEntity<>(lostItemService.getAllLostItemsWithUserDetails(), HttpStatus.OK);
    }



    @Operation(summary = "Read All Lost Items", description = " A REST API user endpoint to read the saved Lost Items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description ="Internal Server Error.")
    })
    @GetMapping("/user/getAllLostItems")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Set<LostItemDto>> getLostItems() {
        return new ResponseEntity<>(lostItemService.getAllLostItems(), HttpStatus.OK);
    }

}
