package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    UserResponseDto add(UserRequestDto userDto);


    UserResponseDto getById(Long id);

    UserResponseDto update(UserRequestDto userDto);

    void delete(Long id);

    List<UserResponseDto> getAll();

    List<OrderResponseDto> getOrders(Long id);

}
