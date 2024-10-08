package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.dto.response.UserResponseDto;
import com.example.finalproject.service.OrderService;
import com.example.finalproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Adding for User")
    @PostMapping
    public ResponseEntity<UserResponseDto> add(@RequestBody UserRequestDto userDto) {
        return new ResponseEntity<>(userService.add(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrders(@PathVariable Long id){
        return ResponseEntity.ok(userService.getOrders(id));
    }


    @Operation(summary = "Get only one User with id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update User")
    @PutMapping
    public ResponseEntity<UserResponseDto> update(@RequestBody UserRequestDto userDto) {
        return new ResponseEntity<>(userService.add(userDto), HttpStatus.OK);

    }


    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "get all Users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}