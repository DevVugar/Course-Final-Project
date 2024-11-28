package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.BasketRequestDto;
import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.BasketResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.entity.Basket;
import com.example.finalproject.model.entity.Product;

import java.util.List;

public interface BasketService {

    ProductResponseDto add(Long userId, Long productId);

    void delete(Long userId, Long productId);

}
