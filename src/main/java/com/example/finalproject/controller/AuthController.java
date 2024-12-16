package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.LoginReq;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.LoginResponse;
import com.example.finalproject.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public LoginResponse login(@Valid @RequestBody LoginReq loginReq)  {
        return authService.login(loginReq);
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequestDto userReqDto)  {
        authService.register(userReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<Void> forgetPassword(String email){
        authService.forgetPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword) {
        try {
            authService.resetPassword(email, newPassword, otp);
            return ResponseEntity.ok("Password reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
