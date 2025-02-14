package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.dto.LoginResponse;
import com.xu.yan.employee_management.model.User;
import com.xu.yan.employee_management.response.ApiResponse;
import com.xu.yan.employee_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ApiResponse<LoginResponse> login(@RequestBody User loginRequest) {
        LoginResponse response = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        log.info("response is:{}", response.getToken());
        if (response != null) {
            return ApiResponse.success(response);
        } else {
            return ApiResponse.error(401, "Invalid username or password");
        }

    }

    @PostMapping("/api/register")
    public ApiResponse<User> register(@RequestBody User user) {
        boolean success = userService.register(user);
        if (success) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(400, "User already exists");
        }
    }

}

