package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.request.CartRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.CartResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.entity.User;

import java.util.List;

public interface CartService {


    CartResponseDto add(CartRequestDto requestDto);

    CartResponseDto getById(Long id);

    CartResponseDto update(CartRequestDto brandDto);

    void delete(Long id);

}
