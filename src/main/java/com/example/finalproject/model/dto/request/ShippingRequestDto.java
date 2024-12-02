package com.example.finalproject.model.dto.request;

import com.example.finalproject.enums.ShippingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShippingRequestDto {
    private Long id;
    private String shippingAddress;
    private String shippingMethod;
    private String trackingNumber;
    private Long paymentId;

}
