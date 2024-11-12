package com.example.finalproject.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
    private String message;
    private int code;
}