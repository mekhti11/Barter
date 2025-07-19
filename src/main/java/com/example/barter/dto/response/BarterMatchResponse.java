package com.example.barter.dto.response;

import com.example.barter.entity.BarterMatch;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BarterMatchResponse {
    
    private Long id;
    private UserResponse user1;
    private UserResponse user2;
    private PostResponse post1;
    private PostResponse post2;
    private BarterMatch.MatchStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private ChatMessageResponse lastMessage;
    private Integer unreadMessagesCount;
    
    public static BarterMatchResponse from(BarterMatch match) {
        BarterMatchResponse response = new BarterMatchResponse();
        response.setId(match.getId());
        response.setUser1(UserResponse.from(match.getUser1()));
        response.setUser2(UserResponse.from(match.getUser2()));
        response.setPost1(PostResponse.from(match.getPost1()));
        response.setPost2(PostResponse.from(match.getPost2()));
        response.setStatus(match.getStatus());
        response.setCreatedAt(match.getCreatedAt());
        response.setUpdatedAt(match.getUpdatedAt());
        response.setCompletedAt(match.getCompletedAt());
        return response;
    }
}