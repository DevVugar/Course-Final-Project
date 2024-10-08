package com.example.finalproject.model.dto.response;

import com.example.finalproject.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class PaymentResponseDto {
    private Long id;
    private Timestamp paymentDate;
    private BigDecimal amount;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
}
