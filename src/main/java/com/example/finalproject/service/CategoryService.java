package com.example.finalproject.service;

import com.example.finalproject.model.entity.Category;

import java.util.List;

public interface CategoryService {

    Category add(Category category);

    Category getById(Long id);

    Category update(Category category);

    void delete(Long id);

    List<Category> getAll();
}
