package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.CategoryRequestDto;
import com.example.finalproject.model.dto.request.WishListRequestDto;
import com.example.finalproject.model.dto.response.CategoryResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.WishListResponseDto;
import com.example.finalproject.model.entity.WishList;

import java.util.List;

public interface WishListService {
    void add(WishListRequestDto requestDto);
    void delete(WishListRequestDto requestDto);
    List<ProductResponseDto> getAll(Long id);
}