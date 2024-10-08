package com.example.finalproject.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Timestamp orderDate;
    private Double totalAmount;
    // private Status status;
    private String shippingAddress;
    private String paymentMethod;
}
