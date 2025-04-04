package com.kmu.anki.backend.security.auth.oauth2.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
@Getter
public class OidcUserDto {
    private String provider;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
}
