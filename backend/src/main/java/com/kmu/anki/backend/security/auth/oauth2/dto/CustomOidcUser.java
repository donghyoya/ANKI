package com.kmu.anki.backend.security.auth.oauth2.dto;

import com.kmu.anki.backend.domain.user.dto.UserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;

@Getter
public class CustomOidcUser extends DefaultOidcUser {
    private final UserDto user;

    public CustomOidcUser(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken, UserDto userDto) {
        super(authorities, idToken);
        this.user = userDto;
    }
}
