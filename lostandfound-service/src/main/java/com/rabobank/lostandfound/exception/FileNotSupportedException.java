package com.rabobank.lostandfound.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileNotSupportedException extends RuntimeException{

    private String message;
    private int status;
    private LocalDateTime timestamp;

    public FileNotSupportedException(String message) {
        super(message);
    }


}
