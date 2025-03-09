package com.kmu.anki.backend.domain.auth.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalUtils {
    public static Long extractUserId(Authentication authentication){
        if(authentication == null){
            throw new NullAuthenticationException();
        }
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        Long userId = (Long) principal.getAttribute("userId");
        return userId;
    }
}
