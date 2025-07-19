package com.example.barter.controller;

import com.example.barter.dto.request.UpdateProfileRequest;
import com.example.barter.dto.response.UserResponse;
import com.example.barter.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getCurrentUserProfile(Authentication authentication) {
        log.info("Getting current user profile for: {}", authentication.getName());
        
        UserResponse userProfile = userService.getCurrentUserProfile(authentication.getName());
        return ResponseEntity.ok(userProfile);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable Long userId) {
        log.info("Getting user profile for user ID: {}", userId);
        
        UserResponse userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }
    
    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(
            @Valid @RequestBody UpdateProfileRequest request,
            Authentication authentication) {
        log.info("Updating profile for user: {}", authentication.getName());
        
        // Get current user ID from authentication
        UserResponse currentUser = userService.getCurrentUserProfile(authentication.getName());
        UserResponse updatedProfile = userService.updateUserProfile(currentUser.getId(), request);
        
        return ResponseEntity.ok(updatedProfile);
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Getting all users");
        
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        log.info("Deleting user with ID: {}", userId);
        
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
