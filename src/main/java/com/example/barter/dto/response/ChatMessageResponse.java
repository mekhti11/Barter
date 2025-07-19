package com.example.barter.dto.response;

import com.example.barter.entity.ChatMessage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageResponse {
    
    private Long id;
    private Long matchId;
    private UserResponse sender;
    private String content;
    private ChatMessage.MessageType messageType;
    private String imageUrl;
    private String fileUrl;
    private Boolean isRead;
    private LocalDateTime createdAt;
    
    public static ChatMessageResponse from(ChatMessage message) {
        ChatMessageResponse response = new ChatMessageResponse();
        response.setId(message.getId());
        response.setMatchId(message.getMatch().getId());
        response.setSender(UserResponse.from(message.getSender()));
        response.setContent(message.getContent());
        response.setMessageType(message.getMessageType());
        response.setImageUrl(message.getImageUrl());
        response.setFileUrl(message.getFileUrl());
        response.setIsRead(message.getIsRead());
        response.setCreatedAt(message.getCreatedAt());
        return response;
    }
}