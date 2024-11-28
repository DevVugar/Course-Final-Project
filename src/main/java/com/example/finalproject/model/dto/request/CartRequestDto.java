package com.example.finalproject.model.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CartRequestDto {
    private String bankAccount;
    private Integer cvv;
    private LocalDate expiryDate;
}
