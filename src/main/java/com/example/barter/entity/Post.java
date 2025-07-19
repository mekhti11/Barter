package com.example.barter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;
    
    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.ACTIVE;
    
    @ElementCollection
    private List<String> imageUrls;
    
    private String location;
    private Double estimatedValue; // Estimated value for reference
    
    // What the user wants in exchange
    @Column(name = "looking_for")
    private String lookingFor;
    
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Category> interestedCategories;
    
    private Integer viewCount = 0;
    private Integer likeCount = 0;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum Category {
        ELECTRONICS,
        BOOKS,
        CLOTHING,
        FURNITURE,
        SPORTS,
        AUTOMOTIVE,
        HOME_GARDEN,
        TOYS_GAMES,
        MUSIC_INSTRUMENTS,
        ART_CRAFTS,
        JEWELRY,
        TOOLS,
        KITCHEN,
        HEALTH_BEAUTY,
        OTHER
    }
    
    public enum ItemCondition {
        NEW,
        LIKE_NEW,
        GOOD,
        FAIR,
        POOR
    }
    
    public enum PostStatus {
        ACTIVE,      // Available for trading
        TRADED,      // Successfully traded
        INACTIVE,    // Temporarily hidden
        DELETED      // Removed by user
    }
}