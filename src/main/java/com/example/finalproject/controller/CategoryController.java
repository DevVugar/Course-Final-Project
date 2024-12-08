package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.CategoryRequestDto;
import com.example.finalproject.model.dto.response.CategoryResponseDto;
import com.example.finalproject.model.entity.Category;
import com.example.finalproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public CategoryResponseDto add(@RequestBody CategoryRequestDto category) {
        return categoryService.add(category);
    }

    @GetMapping("/get/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PutMapping("/update")
    public CategoryResponseDto update(@RequestBody CategoryRequestDto category) {
        return categoryService.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @GetMapping("/getAll")
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll();
    }
}

