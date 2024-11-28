package com.example.finalproject.exception;

import com.example.finalproject.model.entity.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class PaymentException extends RuntimeException{
    public PaymentException(String message){
        super(message);
    }
}
