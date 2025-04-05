package com.kmu.anki.backend.security.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmu.anki.backend.global.controller.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * GlobalAdvice와 동일한 응답을 할 수 있도록 조치
 */
@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ExceptionResponse exceptionResponse = ExceptionResponse.of(
                HttpServletResponse.SC_UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.getReasonPhrase()
        );

        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }
}
