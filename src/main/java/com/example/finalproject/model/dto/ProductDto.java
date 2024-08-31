package com.example.finalproject.model.dto;

import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.model.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String imageUrl;

    private BrandDto brand;
    private CategoryDto category;


}
