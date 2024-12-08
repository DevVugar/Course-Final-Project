package com.example.finalproject.model.dto.response;

import com.example.finalproject.enums.ShippingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShippingResponseDto {
    private Long id;
    private String shippingAddress;
    private String shippingMethod;
    private String customerName;
    //private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;
}
