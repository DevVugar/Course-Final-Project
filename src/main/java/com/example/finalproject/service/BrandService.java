package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandService {

    List<ProductResponseDto> getProductsByBrandId(Long brandId);

    BrandResponseDto add(BrandRequestDto brandDto);

    BrandResponseDto getById(Long id);

    BrandResponseDto update(BrandRequestDto brandDto);

    void delete(Long id);

    List<BrandResponseDto> getAll();

   // ResponseEntity<?> get(Long id);


}
