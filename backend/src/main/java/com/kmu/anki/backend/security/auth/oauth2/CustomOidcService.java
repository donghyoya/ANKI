package com.kmu.anki.backend.security.auth.oauth2;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.kmu.anki.backend.domain.auth.legacy.vo.Role;
import com.kmu.anki.backend.domain.user.dto.CreateUserDto;
import com.kmu.anki.backend.domain.user.dto.UserDto;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.service.UserService;
import com.kmu.anki.backend.security.auth.oauth2.dto.CustomOidcUser;
import com.kmu.anki.backend.security.auth.oauth2.dto.OidcUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustomOidcService extends OidcUserService{
    private final UserService userService;
    private final GoogleOidcUserMapper oidcUserMapper;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oAuth2User = super.loadUser(userRequest);
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        OidcUserDto userInfo = oidcUserMapper.map(oAuth2User, clientRegistration);
        UserDto userDetails = loadUserDetails(userInfo);

        Collection<? extends GrantedAuthority> grantedAuthorities = userInfo.getAuthorities();

        return new CustomOidcUser(
                grantedAuthorities,
                oAuth2User.getIdToken(),
                userDetails
        );
    }

    private UserDto loadUserDetails(OidcUserDto userInfo){
        Optional<User> user = userService.findUserByUsername(userInfo.getUsername());
        UserDto result = null;
        if(user.isPresent()){
            result = UserDto.of(user.get());
        }else {
            CreateUserDto createUser = CreateUserDto.builder()
                    .name(userInfo.getUsername())
                    .role(Role.USER)
                    .build();
            User newUser = userService.saveUser(createUser);
            result = UserDto.of(newUser);
        }
        return result;
    }

}
