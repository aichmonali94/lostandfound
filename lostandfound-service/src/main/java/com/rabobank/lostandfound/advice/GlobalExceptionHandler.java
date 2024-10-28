package com.rabobank.lostandfound.advice;

import com.rabobank.lostandfound.exception.ClaimException;
import com.rabobank.lostandfound.exception.FileNotSupportedException;
import com.rabobank.lostandfound.exception.NoLostItemsFoundException;
import com.rabobank.lostandfound.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(FileNotSupportedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),ex.getStatus(),ex.getTimestamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoLostItemsFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemsNotFoundException(NoLostItemsFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),ex.getStatus(),ex.getTimestamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClaimException.class)
    public ResponseEntity<ErrorResponse> handleClaimException(ClaimException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),ex.getStatus(),ex.getTimestamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
