package io.lpamintuan.springmultipartupload.controllers;

import java.util.HashMap;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UploadControllerAdvice {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleConstraintValidationException(ConstraintViolationException ex) {

        HashMap<String, String> error = new HashMap<>();

        Optional<ConstraintViolation<?>> x = ex.getConstraintViolations().stream()
            .findFirst();

        error.put("error", x.get().getMessage());

        return error;
        
    }
    
}
