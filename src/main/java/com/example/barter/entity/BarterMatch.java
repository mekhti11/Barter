package com.example.barter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "barter_matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarterMatch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post1_id", nullable = false)
    private Post post1; // Post from user1 that user2 liked
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post2_id", nullable = false)
    private Post post2; // Post from user2 that user1 liked
    
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.ACTIVE;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // When the trade was completed
    private LocalDateTime completedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum MatchStatus {
        ACTIVE,      // Match created, users can chat
        NEGOTIATING, // Users are discussing trade details
        AGREED,      // Terms agreed, meeting arranged
        COMPLETED,   // Trade completed successfully
        CANCELLED,   // One user cancelled
        EXPIRED      // Match expired due to inactivity
    }
}
