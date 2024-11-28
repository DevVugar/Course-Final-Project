package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.CategoryRequestDto;
import com.example.finalproject.model.dto.response.CategoryResponseDto;
import com.example.finalproject.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = {ProductMapping.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapping {


    Category toEntity(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toResponse(Category category);

    void toUpdate(CategoryRequestDto categoryRequestDto, @MappingTarget Category category);
}

