package com.example.possystem.exception;

import com.example.possystem.base.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ValidateException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        Map<String, Object> errorDto = new HashMap<>();

        List<FieldError> errors = new ArrayList<>();

        exception.getFieldErrors().forEach(fieldError -> errors.add(new FieldError(fieldError.getField(), fieldError.getDefaultMessage())));

        errorDto.put("errors", errors);

        return errorDto;
    }

}
