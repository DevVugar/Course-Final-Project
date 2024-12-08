package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.dto.request.CardRequestDto;
import com.example.finalproject.model.dto.response.CardResponseDto;
import com.example.finalproject.model.entity.Card;
import com.example.finalproject.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapping {
    Card toEntity(CardRequestDto requestDto);
    List<Card> toEntity(List<CardRequestDto> requestDto);

    CardResponseDto toResponse(Card cart);
    void toUpdate(CardRequestDto requestDto, @MappingTarget Card cart);

}
