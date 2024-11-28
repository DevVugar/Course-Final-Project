package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.request.PaymentRequestWithCardDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;
import com.example.finalproject.model.entity.Payment;
import com.example.finalproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapping {

    Payment toEntity(PaymentRequestDto requestDto);
    Payment toEntity(PaymentRequestWithCardDto paymentRequestWithCardDto);
    PaymentResponseDto toResponse(Payment payment);


    void toUpdate(PaymentRequestDto requestDto, @MappingTarget Payment payment);
}
