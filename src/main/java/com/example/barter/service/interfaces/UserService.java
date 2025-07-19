package com.example.barter.service.interfaces;

import com.example.barter.dto.request.UpdateProfileRequest;
import com.example.barter.dto.response.UserResponse;
import com.example.barter.entity.User;

import java.util.List;

public interface UserService {
    UserResponse getUserProfile(Long userId);
    UserResponse updateUserProfile(Long userId, UpdateProfileRequest request);
    UserResponse getCurrentUserProfile(String email);
    List<UserResponse> getAllUsers();
    void deleteUser(Long userId);
    User findByEmail(String email);
    User findById(Long userId);
}