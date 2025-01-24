package com.kmu.anki.backend.global.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(NoSuchElementException ex) {
        return new ResponseEntity<>(ExceptionResponse.of(404, "NOT FOUND"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException ex){
        return new ResponseEntity<>(ExceptionResponse.of(500, "INTERNAL_SERVER_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
