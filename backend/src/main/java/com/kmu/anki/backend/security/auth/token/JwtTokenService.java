package com.kmu.anki.backend.security.auth.token;

import com.kmu.anki.backend.security.auth.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtTokenService {
    private final Key KEY;

    private final long EXPIRATION = 24*60*60*1000;
    private final long ACCESS_TOKEN_EXPIRATION = 1000*60*60*24; // 1일
    private final long REFRESH_TOKEN_EXPIRATION = 1000*60*60*24*7; // 1주일

    private final long AUTHENTICATION_TOKEN_EXPIRATION = 1000*60*10; // 10분

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
    @Deprecated
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

    public String generateAccessToken(String subject, TokenClaimBuilder builder){
        return generateToken(subject, ACCESS_TOKEN_EXPIRATION, builder.build());
    }

    public String generateRefreshToken(String subject, TokenClaimBuilder builder){
        return generateToken(subject, REFRESH_TOKEN_EXPIRATION, builder.buildRefresh());
    }


    public String generateAuthenticationToken(String subject, Map<String, Object> claims){
        return generateToken(subject, AUTHENTICATION_TOKEN_EXPIRATION, claims);
    }

    private String generateToken(String subject, Long expiration, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiration))
                .signWith(KEY, signatureAlgorithm)
                .compact();
    }


    public TokenClaimsDto validateAndGetClaims(String token){
        try {
            Claims claims = getClaims(token);
            String subject = claims.getSubject();
            Long userId = claims.get("userId", Long.class);
            String role = claims.get("role", String.class);
            if(role == null){
                throw new InvalidTokenException();
            }
            return new TokenClaimsDto(subject, userId,role);
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
            return false;
        } catch(JwtException | IllegalArgumentException ex) {
            // 잘못된 토큰
            return false;
        }catch (RuntimeException ex){
            return false;
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
