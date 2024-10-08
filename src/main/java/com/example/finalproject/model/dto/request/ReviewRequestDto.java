package com.example.finalproject.model.dto.request;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private Long id;
    private Integer rating;
    private String comment;

    private UserRequestDto user;
    private ProductRequestDto product;



}
