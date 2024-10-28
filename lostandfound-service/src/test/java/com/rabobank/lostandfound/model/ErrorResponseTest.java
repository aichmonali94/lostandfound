package com.rabobank.lostandfound.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ErrorResponseTest {

    @Test
    public void testErrorResponseConstructorAndGetters() {
        String message = "An error occurred";
        int status = 404;
        LocalDateTime timestamp = LocalDateTime.now();

        ErrorResponse errorResponse = new ErrorResponse(message, status, timestamp);

        assertThat(errorResponse.getMessage()).isEqualTo(message);
        assertThat(errorResponse.getStatus()).isEqualTo(status);
        assertThat(errorResponse.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    public void testErrorResponseNoArgsConstructor() {
        ErrorResponse errorResponse = new ErrorResponse();

        assertThat(errorResponse.getMessage()).isNull();
        assertThat(errorResponse.getStatus()).isZero();
        assertThat(errorResponse.getTimestamp()).isNull();
    }
}
