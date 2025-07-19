package com.example.barter.service.impl;

import com.example.barter.dto.request.UpdateProfileRequest;
import com.example.barter.dto.response.UserResponse;
import com.example.barter.entity.User;
import com.example.barter.exception.UserNotFoundException;
import com.example.barter.repository.UserRepository;
import com.example.barter.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public UserResponse getUserProfile(Long userId) {
        log.info("Getting user profile for user ID: {}", userId);
        
        User user = findById(userId);
        return UserResponse.from(user);
    }
    
    @Override
    @Transactional
    public UserResponse updateUserProfile(Long userId, UpdateProfileRequest request) {
        log.info("Updating profile for user ID: {}", userId);
        
        User user = findById(userId);
        
        // Update only non-null fields
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }
        if (request.getLocation() != null) {
            user.setLocation(request.getLocation());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getProfileImageUrl() != null) {
            user.setProfileImageUrl(request.getProfileImageUrl());
        }
        
        User updatedUser = userRepository.save(user);
        log.info("Profile updated successfully for user: {}", updatedUser.getEmail());
        
        return UserResponse.from(updatedUser);
    }
    
    @Override
    public UserResponse getCurrentUserProfile(String email) {
        log.info("Getting current user profile for email: {}", email);
        
        User user = findByEmail(email);
        return UserResponse.from(user);
    }
    
    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Getting all users");
        
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        
        User user = findById(userId);
        user.setStatus(User.UserStatus.INACTIVE);
        userRepository.save(user);
        
        log.info("User deactivated successfully: {}", user.getEmail());
    }
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }
    
    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }
}
