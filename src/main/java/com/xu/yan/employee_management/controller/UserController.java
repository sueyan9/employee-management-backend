package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.dto.LoginResponse;
import com.xu.yan.employee_management.model.User;
import com.xu.yan.employee_management.response.ApiResponse;
import com.xu.yan.employee_management.service.UserService;
import com.xu.yan.employee_management.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody User loginRequest) {
        LoginResponse response = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        log.info("response is:{}", response.getToken());
        if (response != null) {
            return ApiResponse.success(response);
        } else {
            return ApiResponse.error(401, "Invalid username or password");
        }

    }

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody User user) {
        boolean success = userService.register(user);
        if (success) {
            return ApiResponse.success();
        } else {
            return ApiResponse.error(400, "User already exists");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            String token = refreshToken.substring(7);
            String username = jwtUtil.extractUsername(token);

            if (jwtUtil.validateRefreshToken(token, username)) {
                String newAccessToken = jwtUtil.generateToken(username);
                return ResponseEntity.ok(newAccessToken);
            } else {
                return ResponseEntity.status(403).body("Invalid or expired refresh token");
            }
        } else {
            return ResponseEntity.badRequest().body("Refresh token missing or malformed");
        }
    }
}

