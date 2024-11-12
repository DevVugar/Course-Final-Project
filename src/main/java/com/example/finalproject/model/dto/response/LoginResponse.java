package com.example.finalproject.model.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
}
