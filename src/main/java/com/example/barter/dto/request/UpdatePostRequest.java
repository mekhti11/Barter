package com.example.barter.dto.request;


import com.example.barter.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePostRequest {
    
    private String title;
    private String description;
    private Post.Category category;
    private Post.ItemCondition itemCondition;
    private List<String> imageUrls;
    private String location;
    private Double estimatedValue;
    private String lookingFor;
    private List<Post.Category> interestedCategories;
    private Post.PostStatus status;
}