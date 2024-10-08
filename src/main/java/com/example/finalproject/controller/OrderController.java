package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.service.BrandService;
import com.example.finalproject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Adding for Order")
    @PostMapping
    public ResponseEntity<OrderResponseDto> add(@RequestBody OrderRequestDto brandDto) {
        return new ResponseEntity<>(orderService.add(brandDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get only one Order with id")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update Order")
    @PutMapping
    public ResponseEntity<OrderResponseDto> update(@RequestBody OrderRequestDto orderDto) {
        return new ResponseEntity<>(orderService.add(orderDto), HttpStatus.OK);

    }


    @Operation(summary = "Delete Order")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "get all Orders")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }
}