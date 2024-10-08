package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.request.ShippingRequestDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;
import com.example.finalproject.model.dto.response.ShippingResponseDto;

import java.util.List;

public interface ShippingService {
    ShippingResponseDto add(ShippingRequestDto requestDto);


    ShippingResponseDto getById(Long id);

    ShippingResponseDto update(ShippingRequestDto requestDto);

    void delete(Long id);

    List<ShippingResponseDto> getAll();
}
