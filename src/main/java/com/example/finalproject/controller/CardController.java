package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.CardRequestDto;
import com.example.finalproject.model.dto.response.CardResponseDto;
import com.example.finalproject.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cartService;

    @PostMapping("/add/{id}")
    public ResponseEntity<CardResponseDto> add(@PathVariable Long id,@RequestBody @Valid CardRequestDto requestDto) {
        return new ResponseEntity<>(cartService.add(id,requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CardResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
