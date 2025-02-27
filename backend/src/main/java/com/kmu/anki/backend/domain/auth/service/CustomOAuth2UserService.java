package com.kmu.anki.backend.domain.auth.service;

import com.kmu.anki.backend.domain.auth.vo.Role;
import com.kmu.anki.backend.domain.user.dto.CreateUserDto;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import com.kmu.anki.backend.domain.user.service.UserService;
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
    private final RestTemplate restTemplate;
    private final UserService userService;

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
                    CreateUserDto newUser = CreateUserDto.builder()
                            .email(email)
                            .name(name)
                            .role(Role.USER)
                            .build();
                    return userService.saveUser(newUser);
                });

        // dto도 아니고 왜 Entity를 여기에 넣는지?
//        httpSession.setAttribute("user", user);
        userAttributes.put("userId", user.getId());
        userAttributes.put("todayStudyWords", user.getTodayStudyWords());
        userAttributes.put("todayReviewWords", user.getTodayReviewWords());
        userAttributes.put("languageCode", user.getLanguageCode());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.USER.getKey())),
                userAttributes,
                "email"
        );
    }

}
