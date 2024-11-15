package com.example.finalproject.service;

import com.example.finalproject.model.dto.UserDto;
import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.*;
import com.example.finalproject.model.entity.Cart;
import com.example.finalproject.model.entity.WishList;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {


    UserResponseDto getById(Long id);

    UserResponseDto update(Long id,UserDto userDto);

    void delete(Long id);

    List<UserResponseDto> getAll();

    List<OrderResponseDto> getOrders(Long id);

    List<ProductResponseDto> getProductByBasket(Long id);

    Set<CartResponseDto> getCarts(Long id);


    List<ProductResponseDto> getWisListProduct(Long id);

}
