package com.example.finalproject.model.dto.response;

import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.User;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WishListResponseDto {
    private UserResponseDto user;
    private List<ProductResponseDto> products;
}
