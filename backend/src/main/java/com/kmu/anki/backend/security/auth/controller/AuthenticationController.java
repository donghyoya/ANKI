package com.kmu.anki.backend.security.auth.controller;

import com.kmu.anki.backend.domain.user.dto.LoginUserDto;
import com.kmu.anki.backend.domain.user.dto.UserDto;
import com.kmu.anki.backend.domain.user.service.UserService;
import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import com.kmu.anki.backend.security.auth.token.TokenClaimBuilder;
import com.kmu.anki.backend.security.auth.token.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @GetMapping("/auth/token")
    public TokenDto getAuthToken(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        LoginUserDto userDto = userService.loadUserDetail(userId);
        TokenClaimBuilder builder = TokenClaimBuilder.builder()
                .userId(userDto.getId())
                .role(userDto.getRole());
        String accessToken = jwtTokenService.generateAccessToken(userId.toString(), builder);
        String refreshToken = jwtTokenService.generateRefreshToken(userId.toString(), builder);

        return new TokenDto(accessToken, refreshToken, userDto.isFirst());
    }
}
