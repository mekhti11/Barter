package com.example.barter.dto.response;

import com.example.barter.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String profileImageUrl;
    private String location;
    private String phoneNumber;
    private User.UserStatus status;
    private Boolean isEmailVerified;
    private Double rating;
    private Integer totalTrades;
    private LocalDateTime createdAt;

    public static UserResponse from(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setBio(user.getBio());
        response.setProfileImageUrl(user.getProfileImageUrl());
        response.setLocation(user.getLocation());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setStatus(user.getStatus());
        response.setIsEmailVerified(user.getIsEmailVerified());
        response.setRating(user.getRating());
        response.setTotalTrades(user.getTotalTrades());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
