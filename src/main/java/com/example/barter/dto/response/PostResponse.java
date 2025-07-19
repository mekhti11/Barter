package com.example.barter.dto.response;

import com.example.barter.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {
    
    private Long id;
    private UserResponse user;
    private String title;
    private String description;
    private Post.Category category;
    private Post.ItemCondition itemCondition;
    private Post.PostStatus status;
    private List<String> imageUrls;
    private String location;
    private Double estimatedValue;
    private String lookingFor;
    private List<Post.Category> interestedCategories;
    private Integer viewCount;
    private Integer likeCount;
    private Boolean isLikedByCurrentUser = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static PostResponse from(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setUser(UserResponse.from(post.getUser()));
        response.setTitle(post.getTitle());
        response.setDescription(post.getDescription());
        response.setCategory(post.getCategory());
        response.setItemCondition(post.getItemCondition());
        response.setStatus(post.getStatus());
        response.setImageUrls(post.getImageUrls());
        response.setLocation(post.getLocation());
        response.setEstimatedValue(post.getEstimatedValue());
        response.setLookingFor(post.getLookingFor());
        response.setInterestedCategories(post.getInterestedCategories());
        response.setViewCount(post.getViewCount());
        response.setLikeCount(post.getLikeCount());
        response.setCreatedAt(post.getCreatedAt());
        response.setUpdatedAt(post.getUpdatedAt());
        return response;
    }
}