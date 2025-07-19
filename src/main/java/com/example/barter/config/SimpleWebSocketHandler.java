package com.example.barter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class SimpleWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("=== WebSocket connection established ===");
        log.info("Session ID: {}", session.getId());
        log.info("Remote Address: {}", session.getRemoteAddress());
        log.info("URI: {}", session.getUri());
        
        // Send welcome message
        try {
            session.sendMessage(new TextMessage("Welcome! Connection successful."));
            log.info("Welcome message sent successfully");
        } catch (Exception e) {
            log.error("Failed to send welcome message: {}", e.getMessage());
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("=== Message received ===");
        log.info("Session: {}", session.getId());
        log.info("Message: {}", payload);
        
        try {
            // Echo the message back
            String response = "Echo: " + payload;
            session.sendMessage(new TextMessage(response));
            log.info("Echo response sent: {}", response);
        } catch (Exception e) {
            log.error("Failed to send echo response: {}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("=== WebSocket connection closed ===");
        log.info("Session ID: {}", session.getId());
        log.info("Close Status: {}", status);
        log.info("Close Code: {}", status.getCode());
        log.info("Close Reason: {}", status.getReason());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("=== WebSocket transport error ===");
        log.error("Session ID: {}", session.getId());
        log.error("Error: {}", exception.getMessage(), exception);
    }
}