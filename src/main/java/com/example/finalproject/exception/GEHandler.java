package com.example.finalproject.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GEHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO handleNotFound(NotFoundException ex) {
        log.error("Action.error.log -> NOT_FOUND: {}", ex.getMessage());
        return ExceptionDTO.builder().message(ex.getMessage()).code(HttpStatus.NOT_FOUND.value()).build();
    }



    @ExceptionHandler({AlreadyExistsException.class})
    public ExceptionDTO handleStudentAlreadyExistsException(AlreadyExistsException ex) {
        log.error("Action.error.log -> Already Taken: {}", ex.getMessage());
        return ExceptionDTO.builder().message(ex.getMessage()).code(HttpStatus.NOT_FOUND.value()).build();
    }

    @ExceptionHandler({PaymentException.class})
    public ExceptionDTO handlePaymentProcess(AlreadyExistsException ex) {
        log.error("Payment processing error -> {}", ex.getMessage());
        return ExceptionDTO.builder().message(ex.getMessage()).code(HttpStatus.NOT_FOUND.value()).build();
    }



}