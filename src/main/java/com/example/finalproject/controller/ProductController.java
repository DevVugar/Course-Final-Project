package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseAdminDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.entity.Product;
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


    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> add(@RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.add(requestDto));
    }


    @GetMapping("/name")
    public ResponseEntity<List<ProductResponseDto>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @Operation(summary = "get reviews by product")
    @GetMapping("{id}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getReviewByProduct(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getReviewByProduct(id));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseAdminDto>> getLowStockProducts() {
        return ResponseEntity.ok(productService.getLowStockProducts());
    }

    @GetMapping("/close-to-expiration")
    public ResponseEntity<List<ProductResponseAdminDto>> getCloseToExpirationProducts() {
        return ResponseEntity.ok(productService.getCloseToExpirationProducts());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.update(id,requestDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
}

