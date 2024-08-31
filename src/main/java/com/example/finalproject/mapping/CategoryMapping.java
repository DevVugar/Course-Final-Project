package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.CategoryDto;
import com.example.finalproject.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapping {


    CategoryDto getCategoryDto(Category category);
}

