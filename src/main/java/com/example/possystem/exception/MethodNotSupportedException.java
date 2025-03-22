package com.example.possystem.exception;

import com.example.possystem.base.MethodNotSupportError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MethodNotSupportedException {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){

        var methodError = MethodNotSupportError.builder()
                .message(e.getMessage())
                .code(e.getStatusCode().toString())
                .method(e.getMethod())
                .status("API not found....")
                .build();

        return new ResponseEntity<>(methodError, e.getStatusCode());
    }

}
