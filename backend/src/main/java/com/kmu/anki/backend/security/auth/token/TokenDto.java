package com.kmu.anki.backend.security.auth.token;

import lombok.Getter;

@Getter
public class TokenDto {
    private Long userId;
    private String role;

    public TokenDto(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}
