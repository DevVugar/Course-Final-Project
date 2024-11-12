package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.ReviewRequestDto;
import com.example.finalproject.model.dto.request.ShippingRequestDto;
import com.example.finalproject.model.dto.response.ShippingResponseDto;
import com.example.finalproject.model.entity.Review;
import com.example.finalproject.model.entity.Shipping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShippingMapping {

    Shipping toEntity(ShippingRequestDto responseDto);

    ShippingResponseDto toResponse(Shipping shipping);

    void toUpdate(ShippingRequestDto requestDto, @MappingTarget Shipping shipping);

}
