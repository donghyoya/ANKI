package com.kmu.anki.backend.global.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(NoSuchElementException ex) {
        log.error("[404] NoSuchElementException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ExceptionResponse.of(404, "NOT FOUND"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequest(RuntimeException ex){
        log.error("[400] MethodArgumentNotValidException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ExceptionResponse.of(400, "query parameter is invalid"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException ex){
        log.error("[500] RuntimeException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ExceptionResponse.of(500, "INTERNAL_SERVER_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
