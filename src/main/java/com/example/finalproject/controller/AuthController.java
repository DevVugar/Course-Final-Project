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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public LoginResponse login(@RequestBody LoginReq loginReq)  {
        return authService.login(loginReq);
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequestDto userReqDto)  {
        authService.register(userReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/forgetPassword")
    public void forgetPassword(HttpServletRequest request){
        request.getParameter("user");

    }
}
