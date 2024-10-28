package com.rabobank.lostandfound.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoLostItemsFoundException extends RuntimeException{

    private String message;
    private int status;
    private LocalDateTime timestamp;

    public NoLostItemsFoundException(String message) {
        super(message);
    }
}
