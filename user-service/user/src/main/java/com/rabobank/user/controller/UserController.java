package com.rabobank.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userservice")
public class UserController {

    @GetMapping("/users/{userId}")
    public String getUserNameById(@PathVariable("userId") Long userId) {
        // Mock implementation
        return switch (userId.intValue()) {
            case 1001 -> "User1001";
            case 1002 -> "User1002";
            default -> "Unknown User";
        };
    }
}
