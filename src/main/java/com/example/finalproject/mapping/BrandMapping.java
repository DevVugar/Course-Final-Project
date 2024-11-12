package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.BrandDto;
import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.request.CategoryRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.CategoryResponseDto;
import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapping.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BrandMapping {


    Brand toEntity(BrandRequestDto brandRequestDto);

    BrandResponseDto toResponse(Brand brand);

    void toUpdate(BrandRequestDto brandRequestDto, @MappingTarget Brand brand);
}
