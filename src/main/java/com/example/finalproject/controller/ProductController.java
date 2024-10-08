package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponseDto> add(@RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.add(requestDto));
    }


    @GetMapping("/name")
    public ResponseEntity<List<ProductResponseDto>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @Operation(summary = "get reviews by product")
    @GetMapping("{id}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getReviewByProduct(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getReviewByProduct(id));
    }


    @PutMapping
    public ResponseEntity<ProductResponseDto> update(@RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.update(requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
}

