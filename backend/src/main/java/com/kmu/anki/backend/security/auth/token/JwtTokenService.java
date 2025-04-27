package com.kmu.anki.backend.security.auth.token;

import com.kmu.anki.backend.security.auth.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenService {
    private final Key KEY;

    private final long EXPIRATION = 24*60*60*1000;

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private final JwtParser parser;

    public JwtTokenService(String secretKey) {
        this.KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.parser = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build();
    }

    /**
     * 토큰 생성기
     * @param subject 토큰의 대상 (ID등)
     * @param claims 토큰에 대한 추가 데이터
     * @return 완성된 토큰 반환 
     */
    public String generateToken(String subject, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXPIRATION))
                .signWith(KEY, signatureAlgorithm)
                .compact();
    }

    public TokenDto validateAndGetClaims(String token){
        try {
            Claims claims = getClaims(token);
            String userId = claims.getSubject();
            String role = claims.get("role", String.class);
            return new TokenDto(Long.parseLong(userId), role);
        } catch (ExpiredJwtException ex){
            // 만료된 토큰
            throw ex;
        } catch(JwtException | IllegalArgumentException ex) {
            // 잘못된 토큰
            throw new InvalidTokenException();
        }catch (RuntimeException ex){
            throw new InvalidTokenException();
        }

    }

    /**
     * 토큰 검증 
     * @param token 검증할 JWT 토큰 
     * @return 토큰 검증 결과 
     */
    @Deprecated
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return true;
        } catch (ExpiredJwtException ex){
            // 만료된 토큰
            throw ex;
        } catch(JwtException | IllegalArgumentException ex) {
            // 잘못된 토큰
            throw new InvalidTokenException();
        }catch (RuntimeException ex){
            throw new InvalidTokenException();
        }
    }

    // Claims 추출
    public Claims getClaims(String token) {
        return parser.parseClaimsJws(token).getBody();
    }

    // Subject 추출
    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }


}
