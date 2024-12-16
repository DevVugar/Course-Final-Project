package com.example.finalproject.model.dto.request;


import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Data
public class UserRequestDto {
    @NotBlank(message = "Username or email cannot be empty or null")
    @Size(max = 50)
    @Pattern(regexp = "[A-Za-z0-9_.@]+$")
    private String username;

    @Email
    private String email;

    @NotBlank(message = "Password cannot be empty or null")
    @Pattern(regexp = "[A-Za-z0-9_.@]+$", message="the value must be positive integer")
    @Size(min = 8)
    private String password;
}
