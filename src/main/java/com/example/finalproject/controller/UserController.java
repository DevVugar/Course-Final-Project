package com.example.finalproject.controller;

import com.example.finalproject.model.dto.UserDto;
import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.CartResponseDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.UserResponseDto;
import com.example.finalproject.service.OrderService;
import com.example.finalproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @Operation(summary = "Get only one User with id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update User")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(id,userDto), HttpStatus.OK);

    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "get all Users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(@RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        return ResponseEntity.ok(userService.getAll());
    }


    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrders(@PathVariable Long id){
        return ResponseEntity.ok(userService.getOrders(id));
    }


    @GetMapping("/{id}/basket")
    public ResponseEntity<List<ProductResponseDto>> getProductByBasket(@PathVariable Long id){
        return ResponseEntity.ok(userService.getProductByBasket(id));
    }

    @GetMapping("/{id}/carts")
    public ResponseEntity<Set<CartResponseDto>> getCarts(@PathVariable Long id){
        return ResponseEntity.ok(userService.getCarts(id));
    }



    @GetMapping("/{id}/wishList")
    public ResponseEntity<List<ProductResponseDto>> getWisListByUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getWisListProduct(id));
    }
}