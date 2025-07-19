package com.example.barter.dto.request;

import com.example.barter.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreatePostRequest {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Category is required")
    private Post.Category category;
    
    @NotNull(message = "Item condition is required")
    private Post.ItemCondition itemCondition;
    
    private List<String> imageUrls;
    private String location;
    private Double estimatedValue;
    
    @NotBlank(message = "What you're looking for is required")
    private String lookingFor;
    
    private List<Post.Category> interestedCategories;
}