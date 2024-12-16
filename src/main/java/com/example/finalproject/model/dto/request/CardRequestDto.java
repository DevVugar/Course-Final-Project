package com.example.finalproject.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class  CardRequestDto {
    @NotBlank(message = "Bank account cannot be empty")
    @Size(min = 10, max = 20, message = "Bank account must be between 10 and 20 characters")
    private String bankAccount;

    @NotNull(message = "CVV cannot be null")
    @Min(value = 100, message = "CVV must be a 3-digit number")
    @Max(value = 999, message = "CVV must be a 3-digit number")
    private Integer cvv;

    @NotNull(message = "Expiry date cannot be null")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;
}
