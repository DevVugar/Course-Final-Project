package com.example.finalproject.service;

import com.example.finalproject.model.dto.BrandDto;
import com.example.finalproject.model.entity.Brand;

import java.util.List;

public interface BrandService {

    BrandDto add(Brand brand);

    BrandDto getById(Long id);

    BrandDto update(Brand brand);

    void delete(Long id);

    List<BrandDto> getAll();


}
