package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.dto.response.UserResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto add(OrderRequestDto OrderDto);


    OrderResponseDto getById(Long id);

    OrderResponseDto update(OrderRequestDto OrderDto);

    void delete(Long id);

    List<OrderResponseDto> getAll();
}
