package com.example.finalproject.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketRequestDto {
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
