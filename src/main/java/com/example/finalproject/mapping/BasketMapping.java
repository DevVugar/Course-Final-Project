package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.BasketRequestDto;
import com.example.finalproject.model.dto.response.BasketResponseDto;
import com.example.finalproject.model.entity.Basket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketMapping {

    Basket toEntity(BasketRequestDto requestDto);

    BasketResponseDto toResponse(Basket orderItem);


}
