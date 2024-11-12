package com.example.finalproject.model.dto.request;

import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.User;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WishListRequestDto {

    private Long userId;
    private Long productId;
}
