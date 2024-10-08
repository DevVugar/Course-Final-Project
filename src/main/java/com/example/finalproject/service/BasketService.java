package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.BasketRequestDto;
import com.example.finalproject.model.dto.response.BasketResponseDto;
import com.example.finalproject.model.entity.Basket;

import java.util.List;

public interface BasketService {

    BasketResponseDto add(BasketRequestDto requestDto);

    BasketResponseDto getById(Long id);

    BasketResponseDto update(BasketRequestDto requestDto);

    void delete(Long id);

    List<BasketResponseDto> getAll();
}
