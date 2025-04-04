package com.kmu.anki.backend.security.auth.oauth2;

import com.kmu.anki.backend.security.auth.oauth2.dto.OidcUserDto;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GoogleOidcUserMapper {
    private final String providerName = "google";

    public OidcUserDto map(OidcUser oidcUser, ClientRegistration clientRegistration) {
        return OidcUserDto.builder()
                .username(oidcUser.getAttribute("email"))
                .provider(clientRegistration.getRegistrationId())
                .password(UUID.randomUUID().toString())
                .email(oidcUser.getAttribute("email"))
                .authorities(oidcUser.getAuthorities().stream().toList())
                .build();
    }

}
