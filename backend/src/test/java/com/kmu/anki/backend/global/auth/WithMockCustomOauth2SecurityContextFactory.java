package com.kmu.anki.backend.global.auth;

import com.kmu.anki.backend.domain.auth.vo.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WithMockCustomOauth2SecurityContextFactory implements WithSecurityContextFactory<WithMockCustomOAuth2> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomOAuth2 annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", annotation.name());
        attributes.put("email", annotation.email());
        attributes.put("userId", 1L);

        OAuth2User principal = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.USER.getKey())),
                attributes,
                "email"
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                null,
                principal.getAuthorities()
        );



        context.setAuthentication(authentication);
        return context;
    }
}
