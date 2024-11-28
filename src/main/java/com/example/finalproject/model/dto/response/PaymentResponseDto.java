package com.example.finalproject.model.dto.response;

import com.example.finalproject.enums.PaymentStatus;
import com.example.finalproject.model.entity.Basket;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDto {
    private Long id;
    private LocalDateTime paymentDate;
    private Double totalAmount;
    private String paymentMethod;
    private PaymentStatus paymentStatus;

}
