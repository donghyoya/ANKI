package com.kmu.anki.backend.security.token;

import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import com.kmu.anki.backend.security.auth.token.TokenDto;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenServiceTest {

    private JwtTokenService jwtTokenService;
    private final String secretKey = "YourSecretKeyYourSecretKeyYourSecretKeyYourSecretKey=="; // 256bit Base64 문자열

    @BeforeEach
    void setUp() {
        jwtTokenService = new JwtTokenService(secretKey);
    }

    @Test
    @DisplayName("JWT 토큰 생성 및 유효성 검사 성공 테스트")
    void generateAndValidateToken_success() {
        // given
        String subject = "testUser";
        String role = "ROLE_ADMIN";
        Map<String, Object> claims = Map.of("role", role);

        // when
        String token = jwtTokenService.generateToken(subject, claims);

        // then
        assertNotEquals("", token);
        assertEquals(true, jwtTokenService.validateToken(token));

        Claims parsedClaims = jwtTokenService.getClaims(token);
        assertEquals(subject, parsedClaims.getSubject());
        assertEquals(role, parsedClaims.get("role"));
    }

    @Test
    @DisplayName("잘못된 토큰 검증 실패 테스트")
    void validateToken_invalidToken() {
        // given
        String invalidToken = "invalid.token.here";

        // when
        boolean isValid = jwtTokenService.validateToken(invalidToken);

        // then
        assertEquals(false, isValid);
    }

    @Test
    @DisplayName("토큰 검증 및 claims 추출 테스트")
    void validateAndGetClaimsTest(){
        // given
        String subject = "testUser";
        String role = "ROLE_ADMIN";
        Map<String, Object> claims = Map.of("role", role);

        // when
        String token = jwtTokenService.generateToken(subject, claims);

        // then
        assertNotEquals("", token);
        TokenDto tokenDto = jwtTokenService.validateAndGetClaims(token);

        assertEquals(subject, tokenDto.getSubject());
        assertEquals(role, tokenDto.getRole());
    }
}