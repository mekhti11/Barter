package com.example.barter.dto.request;

import lombok.Data;

@Data
public class LikePostRequest {
    
    private String offerDescription;
    private Long offeredPostId; // Optional: specific post being offered in exchange
}
