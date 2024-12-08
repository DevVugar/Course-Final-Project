package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.BasketRequestDto;
import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.BasketResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;


    @PostMapping("/add/{id}")
    public ResponseEntity<ProductResponseDto> add(@PathVariable Long id, @RequestParam Long productId) {
        return new ResponseEntity<>(basketService.add(id,productId), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long productId) {
        basketService.delete(id,productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
