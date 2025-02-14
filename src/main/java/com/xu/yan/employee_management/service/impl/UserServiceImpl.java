package com.xu.yan.employee_management.service.impl;

import com.xu.yan.employee_management.dto.LoginResponse;
import com.xu.yan.employee_management.mapper.UserMapper;
import com.xu.yan.employee_management.model.User;
import com.xu.yan.employee_management.service.UserService;
import com.xu.yan.employee_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(username);
            return new LoginResponse(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public boolean register(User user) {

        if (userMapper.findByUsername(user.getUsername()) != null) {
            System.out.println("User already exists: " + user.getUsername());
            return false;
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));  // Ensure password is encoded
            userMapper.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

}