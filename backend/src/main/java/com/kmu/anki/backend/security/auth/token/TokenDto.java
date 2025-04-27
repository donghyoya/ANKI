package com.kmu.anki.backend.security.auth.token;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private boolean isFirstLogin;

    public TokenDto(String accessToken, String refreshToken, boolean isFirstLogin) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isFirstLogin = isFirstLogin;
    }
}
