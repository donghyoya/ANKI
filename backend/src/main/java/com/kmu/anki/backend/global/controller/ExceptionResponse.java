package com.kmu.anki.backend.global.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private Integer code;
    private String message;

    public static ExceptionResponse of(Integer code, String message){
        return builder()
                .code(code)
                .message(message)
                .build();
    }
}
