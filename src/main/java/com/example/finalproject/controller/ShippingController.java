package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.request.ShippingRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.dto.response.ShippingResponseDto;
import com.example.finalproject.service.ProductService;
import com.example.finalproject.service.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;


    @PostMapping("/add")
    public ResponseEntity<ShippingResponseDto> add(@RequestBody ShippingRequestDto requestDto) {
        return ResponseEntity.ok(shippingService.add(requestDto));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ShippingResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(shippingService.getById(id));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ShippingResponseDto> update(@PathVariable Long id,@RequestParam String shippingAddress) {
        return ResponseEntity.ok(shippingService.update(id,shippingAddress));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<ShippingResponseDto>> getAll() {
        return ResponseEntity.ok(shippingService.getAll());
    }
}
