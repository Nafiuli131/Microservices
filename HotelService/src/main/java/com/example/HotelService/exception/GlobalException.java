package com.example.HotelService.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFound ex){
        return ResponseEntity.ok(ex.getMessage());
    }
}
