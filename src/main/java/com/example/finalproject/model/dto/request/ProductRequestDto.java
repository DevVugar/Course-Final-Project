package com.example.finalproject.model.dto.request;

import com.example.finalproject.model.dto.BrandDto;
import com.example.finalproject.model.dto.CategoryDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate expirationDate;
    private Integer stockQuantity;
    private String imageUrl;
    private BrandDto brand;
    private CategoryDto category;
}
