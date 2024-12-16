package com.example.finalproject.model.dto.request;

import com.example.finalproject.enums.PaymentMethod;
import com.example.finalproject.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class PaymentRequestDto {

    private Long userId;
    private String paymentMethod;
    private Double shippingAmount;
    private Long cardId;

}
