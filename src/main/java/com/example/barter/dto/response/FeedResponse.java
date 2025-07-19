package com.example.barter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FeedResponse {
    
    private List<PostResponse> posts;
    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;
    private Boolean hasNext;
    private Boolean hasPrevious;
}