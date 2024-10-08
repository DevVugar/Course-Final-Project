package com.example.finalproject.model.dto.request;

import com.example.finalproject.enums.Role;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private String address;
    private String phoneNumber;
    private Role role;
}
