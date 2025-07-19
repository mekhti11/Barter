package com.example.barter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Register without /api prefix since Spring Security context path adds it
        registry.addHandler(new SimpleWebSocketHandler(), "/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();

        // Also register with /api prefix just in case
        registry.addHandler(new SimpleWebSocketHandler(), "/api/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}