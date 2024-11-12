package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.ReviewRequestDto;
import com.example.finalproject.model.dto.request.WishListRequestDto;
import com.example.finalproject.model.dto.response.WishListResponseDto;
import com.example.finalproject.model.entity.Review;
import com.example.finalproject.model.entity.WishList;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WishListMapping {
    WishList toEntity(WishListRequestDto requestDto);
    WishListResponseDto toResponse(WishList wishList);
    void toUpdate(WishListRequestDto productDto, @MappingTarget WishList wishList);

}
