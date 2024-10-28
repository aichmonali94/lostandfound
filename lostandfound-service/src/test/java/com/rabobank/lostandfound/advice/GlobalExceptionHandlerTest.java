package com.rabobank.lostandfound.advice;

import com.rabobank.lostandfound.exception.ClaimException;
import com.rabobank.lostandfound.exception.FileNotSupportedException;
import com.rabobank.lostandfound.exception.NoLostItemsFoundException;
import com.rabobank.lostandfound.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    void handleFileNotSupportedException_whenFileIsEmpty_shouldReturnErrorResponse() {

        FileNotSupportedException exception = new FileNotSupportedException(
                "Please select a file to upload.", 600, LocalDateTime.now());

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Please select a file to upload.", responseEntity.getBody().getMessage());
        assertEquals(600, responseEntity.getBody().getStatus());
    }

    @Test
    void handleFileNotSupportedException_whenContentTypeIsMissing_shouldReturnErrorResponse() {

        FileNotSupportedException exception = new FileNotSupportedException(
                "Content type is missing.", 602, LocalDateTime.now());

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Content type is missing.", responseEntity.getBody().getMessage());
        assertEquals(602, responseEntity.getBody().getStatus());
    }

    @Test
    void handleItemsNotFoundException_whenNoLostItemsFound_shouldReturnErrorResponse() {

        NoLostItemsFoundException exception = new NoLostItemsFoundException(
                "No lost items found.", 605, LocalDateTime.now());

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleItemsNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No lost items found.", responseEntity.getBody().getMessage());
        assertEquals(605, responseEntity.getBody().getStatus());
    }

    @Test
    void handleClaimException_whenNotEnoughQuantity_shouldReturnErrorResponse() {

        ClaimException exception = new ClaimException(
                "Not enough quantity available to claim.", 608, LocalDateTime.now());

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleClaimException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Not enough quantity available to claim.", responseEntity.getBody().getMessage());
        assertEquals(608, responseEntity.getBody().getStatus());
    }


}

