package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseAdminDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ProductResponseDto add(ProductRequestDto requestDto);

    ProductResponseDto getById(Long id);

    ProductResponseDto update(Long id,ProductRequestDto productRequestDto);

    void delete(Long id);

    List<ProductResponseDto> getAll();

    List<ReviewResponseDto> getReviewByProduct(Long id);

    List<ProductResponseDto> getProductByName(String name);


    List<ProductResponseAdminDto> getLowStockProducts();

    List<ProductResponseAdminDto> getCloseToExpirationProducts();
}
