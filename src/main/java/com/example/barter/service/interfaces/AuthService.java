package com.example.barter.service.interfaces;

import com.example.barter.dto.request.LoginRequest;
import com.example.barter.dto.request.RegisterRequest;
import com.example.barter.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(String refreshToken);
}