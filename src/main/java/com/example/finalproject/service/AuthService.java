package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.LoginReq;
import com.example.finalproject.model.dto.request.UserRequestDto;

import com.example.finalproject.model.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void register(UserRequestDto userReqDto);

    LoginResponse login(LoginReq loginReq);

    void forgetPassword(String email);

    void resetPassword(String email, String otp, String newPassword);
}