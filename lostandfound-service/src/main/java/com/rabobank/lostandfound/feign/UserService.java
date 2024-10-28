package com.rabobank.lostandfound.feign;

import com.rabobank.lostandfound.model.UserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service", url="${user.service.url}")
public interface UserService {

    @GetMapping("/api/v1/userservice/users/{userId}")
    String getUserNameById(@PathVariable("userId") Long userId);
}
