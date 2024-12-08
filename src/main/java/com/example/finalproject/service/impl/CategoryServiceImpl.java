package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.CategoryMapping;
import com.example.finalproject.model.dto.request.CategoryRequestDto;
import com.example.finalproject.model.dto.response.CategoryResponseDto;
import com.example.finalproject.repository.CategoryRepository;
import com.example.finalproject.service.CategoryService;
import com.example.finalproject.model.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapping categoryMapping;

    @Override
    public CategoryResponseDto add(CategoryRequestDto requestDto) {
        log.info("Adding new category");
        Category category = categoryMapping.toEntity(requestDto);

        categoryRepository.save(category);


        log.info("Category added with ID: {}", category.getId());
        return categoryMapping.toResponse(category);
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        log.info("Fetching category with ID: {}", id);
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            log.error("Category not found with ID: {}", id);
            return new NotFoundException("Category not found");
        });
        return categoryMapping.toResponse(category);
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto requestDto) {
        log.info("Updating category");

        Category category = categoryMapping.toEntity(requestDto);

        categoryRepository.save(category);

        log.info("Category updated with ID: {}", category.getId());
        return categoryMapping.toResponse(category);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting category with ID: {}", id);
        categoryRepository.deleteById(id);
        log.info("Category deleted with ID: {}", id);
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        log.info("Fetching all categories");
        return categoryRepository.findAll().stream().map((category ->
                categoryMapping.toResponse(category))).collect(Collectors.toList());
    }
}
