package com.example.finalproject.model.dto.response;

import lombok.Data;

@Data
public class ReviewResponseDto {
    private Long id;
    private Integer rating;
    private String comment;
}
