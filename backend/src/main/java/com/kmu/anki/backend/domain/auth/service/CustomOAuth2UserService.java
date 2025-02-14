package com.kmu.anki.backend.domain.auth.service;

import com.kmu.anki.backend.domain.auth.vo.Role;
import com.kmu.anki.backend.domain.user.dto.CreateUserDto;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final RestTemplate restTemplate;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String accessToken = userRequest.getAccessToken().getTokenValue();

        // 1. Access Token을 사용해 Google API에서 사용자 정보 요청
        String userInfoEndpointUri = "https://www.googleapis.com/oauth2/v3/userinfo";
        Map<String, Object> userAttributes = restTemplate.getForObject(userInfoEndpointUri + "?access_token=" + accessToken, Map.class);

        // 2. 사용자 정보 검증 및 저장
        return saveOrUpdateUser(userAttributes);
    }

    private OAuth2User saveOrUpdateUser(Map<String, Object> userAttributes) {
        String email = (String) userAttributes.get("email");
        String name = (String) userAttributes.get("name");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    CreateUserDto newUser = new CreateUserDto();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setRole(Role.USER); // 기본 권한 설정
                    User insertUser = new User();
                    insertUser.insertUser(newUser);
                    return userRepository.save(insertUser);
                });

        httpSession.setAttribute("user", user);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.USER.getKey())),
                userAttributes,
                "email"
        );
    }

}
