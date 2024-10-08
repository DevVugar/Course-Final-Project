package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.entity.Order;
import com.example.finalproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapping {

    Order toEntity(OrderRequestDto orderRequestDto);

    OrderResponseDto toResponse(Order order);

    void toUpdate(OrderRequestDto orderDto, @MappingTarget Order order);

}
