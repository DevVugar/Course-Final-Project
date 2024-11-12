package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.CartRequestDto;
import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.response.CartResponseDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.service.CartService;
import com.example.finalproject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponseDto> add(@RequestBody CartRequestDto requestDto) {
        return new ResponseEntity<>(cartService.add(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CartResponseDto> update(@RequestBody CartRequestDto requestDto) {
        return new ResponseEntity<>(cartService.add(requestDto), HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
