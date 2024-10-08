package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.ShippingRequestDto;
import com.example.finalproject.model.dto.response.ShippingResponseDto;
import com.example.finalproject.model.entity.Shipping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShippingMapping {

    Shipping toEntity(ShippingRequestDto responseDto);

    ShippingResponseDto toResponse(Shipping shipping);


}
