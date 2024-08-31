package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.BrandDto;
import com.example.finalproject.model.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapping {

    BrandDto toBranDto(Brand brand);
    List<BrandDto> toBranDto(List<Brand> list);
}
