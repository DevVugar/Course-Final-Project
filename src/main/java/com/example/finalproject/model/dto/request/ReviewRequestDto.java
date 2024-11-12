package com.example.finalproject.model.dto.request;

import com.example.finalproject.model.dto.UserDto;
import lombok.Data;

@Data
public class ReviewRequestDto {
    private Integer rating;
    private String comment;

    private Long userId;
    private Long productId;



}
