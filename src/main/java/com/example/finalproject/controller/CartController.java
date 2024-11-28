package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.CartRequestDto;
import com.example.finalproject.model.dto.response.CartResponseDto;
import com.example.finalproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/{id}")
    public ResponseEntity<CartResponseDto> add(@PathVariable Long id,@RequestBody CartRequestDto requestDto) {
        return new ResponseEntity<>(cartService.add(id,requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
