package com.example.finalproject.model.dto.request;

import com.example.finalproject.enums.Role;
import lombok.*;

@Builder
@Data
public class UserRequestDto {
    private String username;
    private String fullName;
    private String password;
}
