package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.BasketRequestDto;
import com.example.finalproject.model.dto.response.BasketResponseDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<BasketResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(basketService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BasketResponseDto> add(@RequestBody BasketRequestDto requestDto) {
        return new ResponseEntity<>(basketService.add(requestDto), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<BasketResponseDto> update(@RequestBody BasketRequestDto requestDto) {
        return new ResponseEntity<>(basketService.add(requestDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        basketService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<List<BasketResponseDto>> getAll() {
        return ResponseEntity.ok(basketService.getAll());
    }

}
