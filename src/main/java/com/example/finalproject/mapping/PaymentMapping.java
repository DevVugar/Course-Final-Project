package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;
import com.example.finalproject.model.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapping {

    Payment toEntity(PaymentRequestDto requestDto);

    PaymentResponseDto toResponse(Payment payment);
}
