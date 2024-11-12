package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.BasketRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.BasketResponseDto;
import com.example.finalproject.model.entity.Basket;
import com.example.finalproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BasketMapping {

    Basket toEntity(BasketRequestDto requestDto);

    BasketResponseDto toResponse(Basket orderItem);

    void toUpdate(BasketRequestDto requestDto, @MappingTarget Basket basket);
}
