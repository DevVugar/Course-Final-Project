package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.request.PaymentRequestWithCardDto;
import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.service.PaymentService;
import com.example.finalproject.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "include cart details")
    @PostMapping("/cart")
    public ResponseEntity<PaymentResponseDto> pay(@RequestBody PaymentRequestWithCardDto requestDto) {
        return ResponseEntity.ok(paymentService.pay(requestDto));
    }

    @Operation(summary = "pay with saved cart")
    @PostMapping("/payWithSavedCard")
    public ResponseEntity<PaymentResponseDto> pay(@RequestBody PaymentRequestDto requestDto ) {
        return ResponseEntity.ok(paymentService.pay(requestDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

}
