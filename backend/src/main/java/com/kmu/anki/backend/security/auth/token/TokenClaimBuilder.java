package com.kmu.anki.backend.security.auth.token;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class TokenClaimBuilder {
    private Long userId;
    private String role;

    public Map<String, Object> build(){
        return Map.of("userId", userId, "role", role);
    }

    public Map<String, Object> buildRefresh(){
        return Map.of("userId", userId, "role", "ROLE_REFRESH");
    }

    public TokenClaimBuilder role(String role) {
        this.role = role;
        return this;
    }

    public TokenClaimBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public static TokenClaimBuilder builder(){
        return new TokenClaimBuilder();
    }
}
