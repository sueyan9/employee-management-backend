package com.xu.yan.employee_management.service;

import com.xu.yan.employee_management.dto.LoginResponse;
import com.xu.yan.employee_management.model.User;

public interface UserService {
    LoginResponse login(String username, String password);
    boolean register(User user);


}