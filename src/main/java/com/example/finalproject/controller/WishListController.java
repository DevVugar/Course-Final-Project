package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.WishListRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;


    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody WishListRequestDto requestDto) {
        wishListService.add(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody WishListRequestDto requestDto) {
        wishListService.delete(requestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<List<ProductResponseDto>> getAll(@PathVariable Long userId) {
        return ResponseEntity.ok(wishListService.getAll(userId));
    }


}
