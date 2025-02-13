package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.dto.LoginRequest;
import com.xu.yan.employee_management.dto.LoginResponse;
import com.xu.yan.employee_management.model.User;
import com.xu.yan.employee_management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String username, @RequestParam String password) {
        LoginResponse response = userService.login(username, password);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
