package com.InsRem.service;

import com.InsRem.dto.LoginRequest;
import com.InsRem.dto.RegisterRequest;
import com.InsRem.entity.Agent;

public interface AuthService {

    Agent register(RegisterRequest request);

    String login(LoginRequest request);

    void changePassword(String oldPassword, String newPassword);
}