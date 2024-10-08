package com.example.finalproject.service.impl;

import com.example.finalproject.mapping.CategoryMapping;
import com.example.finalproject.model.dto.request.CategoryRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.CategoryResponseDto;
import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.repository.CategoryRepository;
import com.example.finalproject.service.CategoryService;
import com.example.finalproject.model.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapping categoryMapping;

    @Override
    public CategoryResponseDto add(CategoryRequestDto requestDto) {
        Category category = categoryMapping.toEntity(requestDto);

        categoryRepository.save(category);

        return categoryMapping.toResponse(category);
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryMapping.toResponse(categoryRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto requestDto) {
        Category category = categoryMapping.toEntity(requestDto);

        categoryRepository.save(category);

        return categoryMapping.toResponse(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll().stream().map((category ->
                categoryMapping.toResponse(category))).collect(Collectors.toList());
    }
}
