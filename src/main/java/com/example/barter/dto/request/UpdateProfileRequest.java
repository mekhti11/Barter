package com.example.barter.dto.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private String phoneNumber;
    private String profileImageUrl;
}