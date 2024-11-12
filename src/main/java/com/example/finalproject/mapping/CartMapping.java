package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.dto.request.CartRequestDto;
import com.example.finalproject.model.dto.response.CartResponseDto;
import com.example.finalproject.model.entity.Cart;
import com.example.finalproject.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartMapping {
    Cart toEntity(CartRequestDto requestDto);
    CartResponseDto toResponse(Cart cart);
    void toUpdate(CartRequestDto requestDto, @MappingTarget Cart cart);

}
