package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.ReviewRequestDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.service.ReviewService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> add(@RequestBody ReviewRequestDto reviewRequestDto) {
        return new ResponseEntity<>(reviewService.add(reviewRequestDto), HttpStatus.CREATED);

        //return ResponseEntity.created(reviewService.add(reviewRequestDto));
    }

    @PutMapping
    public ResponseEntity<ReviewResponseDto> update(@RequestBody ReviewRequestDto reviewRequestDto) {
        return new ResponseEntity<>(reviewService.add(reviewRequestDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAll() {
        return ResponseEntity.ok(reviewService.getAll());
    }
}
