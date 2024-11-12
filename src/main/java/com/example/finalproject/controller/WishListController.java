package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.ShippingRequestDto;
import com.example.finalproject.model.dto.request.WishListRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ShippingResponseDto;
import com.example.finalproject.model.dto.response.WishListResponseDto;
import com.example.finalproject.model.entity.WishList;
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


    @PostMapping
    public ResponseEntity<Void> add(@RequestBody WishListRequestDto requestDto) {
        wishListService.add(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody WishListRequestDto requestDto) {
        wishListService.delete(requestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductResponseDto>> getAll(@PathVariable Long id) {
        return ResponseEntity.ok(wishListService.getAll(id));
    }


}
