package com.example.finalproject.model.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponseAdminDto {
    private Long id;
    private String name;
    private Double price;
    private LocalDate expirationDate;
    private Integer stockQuantity;

}
