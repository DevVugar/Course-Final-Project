package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.CategoryRequestDto;
import com.example.finalproject.model.dto.response.CategoryResponseDto;
import com.example.finalproject.model.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto add(CategoryRequestDto category);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto update(CategoryRequestDto category);

    void delete(Long id);

    List<CategoryResponseDto> getAll();
}
