package com.kmu.anki.backend.security.auth.token;

import lombok.Getter;

@Getter
public class TokenDto {
    private String subject;
    private Long userId;
    private String role;

    public TokenDto(String subject, Long userId, String role) {
        this.subject = subject;
        this.userId = userId;
        this.role = role;
    }
}
