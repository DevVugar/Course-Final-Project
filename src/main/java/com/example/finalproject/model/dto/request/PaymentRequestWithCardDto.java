package com.example.finalproject.model.dto.request;

import com.example.finalproject.enums.PaymentMethod;
import lombok.Data;

import jakarta.validation.constraints.Pattern;


@Data
public class PaymentRequestWithCardDto {
    private Long userId;
    private String paymentMethod;
    private Double shippingAmount;
    private CardRequestDto cart;
}
