package com.example.finalproject.service;

import com.example.finalproject.model.dto.UserDto;
import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.request.ResetPasswordDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.*;
import com.example.finalproject.model.entity.WishList;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {


    UserResponseDto getById(Long id);

    UserResponseDto update(Long id,UserDto userDto);

    void delete(Long id);

    List<UserResponseDto> getAll();

    List<PaymentResponseDto> getPayments(Long id);

    List<ProductResponseDto> getProductByBasket(Long id);

    Set<CardResponseDto> getCart(Long id);


    List<ProductResponseDto> getWisListProduct(Long id);

    String changePassword(ResetPasswordDto resetPasswordDto);
}
