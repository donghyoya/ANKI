package com.kmu.anki.backend.security.auth.filter;

import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import com.kmu.anki.backend.security.auth.token.TokenClaimsDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("[JWT Filter] Authentication check started");

        String token = resolveToken(request);

        if (token != null) {
            try {
                TokenClaimsDto tokenClaimsDto = jwtTokenService.validateAndGetClaims(token);
                String username = tokenClaimsDto.getSubject();
                String role = tokenClaimsDto.getRole();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        List.of(new SimpleGrantedAuthority(role))
                );

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);

                log.info("[JWT Filter] Authentication success for user: {}", username);

            } catch (Exception e) {
                log.warn("[JWT Filter] Authentication failed: {}", e.getMessage());
            }
        } else {
            log.info("[JWT Filter] No token provided");
        }

        filterChain.doFilter(request, response);
    }

    /**
     * header에서 토큰을 꺼내는 작업
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        // 1. Header에 있는지 찾기
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // 2. Header에 없으면 쿠키에서 토큰 찾기
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
