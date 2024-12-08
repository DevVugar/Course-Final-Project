package com.example.finalproject.model.dto;

import lombok.Data;

@Data
public class UserDto {

    //private Long id;
    private String username;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
}
