package com.example.finalproject.model.dto.request;

import lombok.Data;

@Data
public class PaymentRequestWithCardDto {
    private Long userId;
    private String paymentMethod;
  //  private String shippingAddress;
    private Double shippingAmount;
    private CartRequestDto cart;
}
