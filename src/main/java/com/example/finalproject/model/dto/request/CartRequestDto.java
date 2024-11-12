package com.example.finalproject.model.dto.request;

import lombok.Data;

@Data
public class CartRequestDto {
    private Long id;

    private UserRequestDto userRequestDto;
}
