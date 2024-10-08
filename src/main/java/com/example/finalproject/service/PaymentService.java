package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;

import java.util.List;

public interface PaymentService {
    PaymentResponseDto add(PaymentRequestDto requestDto);


    PaymentResponseDto getById(Long id);

    PaymentResponseDto update(PaymentRequestDto requestDto);

    void delete(Long id);

    List<PaymentResponseDto> getAll();
}
