package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.request.PaymentRequestWithCardDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto pay(PaymentRequestDto requestDto);
    PaymentResponseDto pay(PaymentRequestWithCardDto requestDto);


    PaymentResponseDto getById(Long id);

}
