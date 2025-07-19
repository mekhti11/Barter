package com.example.barter.dto.request;

import com.example.barter.entity.ChatMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMessageRequest {
    
    @NotNull(message = "Match ID is required")
    private Long matchId;
    
    @NotBlank(message = "Message content is required")
    private String content;
    
    private ChatMessage.MessageType messageType = ChatMessage.MessageType.TEXT;
    private String imageUrl;
    private String fileUrl;
}