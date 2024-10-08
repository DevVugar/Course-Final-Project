package com.example.finalproject.service.impl;

import com.example.finalproject.exception.PaymentNotFoundException;
import com.example.finalproject.mapping.PaymentMapping;
import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;
import com.example.finalproject.model.entity.Payment;
import com.example.finalproject.repository.PaymentRepository;
import com.example.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapping paymentMapping;

    @Override
    public PaymentResponseDto add(PaymentRequestDto requestDto) {
        Payment payment = paymentMapping.toEntity(requestDto);
        Payment ans = paymentRepository.save(payment);
        return paymentMapping.toResponse(ans);
    }

    @Override
    public PaymentResponseDto getById(Long id) {
        return paymentMapping.toResponse(paymentRepository.findById(id).orElseThrow(()->
                new PaymentNotFoundException("Payment not found")));
    }

    @Override
    public PaymentResponseDto update(PaymentRequestDto requestDto) {
        Payment payment = paymentMapping.toEntity(requestDto);
        Payment ans = paymentRepository.save(payment);
        return paymentMapping.toResponse(ans);
    }


    @Override
    public void delete(Long id) {
       paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentResponseDto> getAll() {
        return paymentRepository.findAll().stream().map(payment->
                paymentMapping.toResponse(payment)).collect(Collectors.toList());
    }
}
