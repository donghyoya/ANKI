package com.kmu.anki.backend.security.auth.token;

import com.kmu.anki.backend.domain.auth.legacy.vo.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class TokenClaimBuilder {
    private Long userId;
    private Role role;

    public Map<String, Object> build(){
        return Map.of("userId", userId, "role", role.getKey());
    }

    public Map<String, Object> buildRefresh(){
        return Map.of("userId", userId, "role", "ROLE_REFRESH");
    }

    public TokenClaimBuilder role(Role role) {
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
