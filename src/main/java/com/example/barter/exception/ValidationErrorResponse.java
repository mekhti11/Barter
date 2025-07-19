package com.example.barter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private int status;
    private String error;
    private Map<String, String> validationErrors;
    private LocalDateTime timestamp;
}