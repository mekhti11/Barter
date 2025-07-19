package com.example.barter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
@EnableCaching
@EnableAsync
@EnableTransactionManagement
public class BarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarterApplication.class, args);
    }
}
