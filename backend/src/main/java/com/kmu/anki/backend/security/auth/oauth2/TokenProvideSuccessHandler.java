package com.kmu.anki.backend.security.auth.oauth2;

import com.kmu.anki.backend.security.auth.oauth2.dto.CustomOidcUser;
import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenProvideSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenService jwtTokenService;

    @Value("${auth.redirect.url:http://localhost:3000/auth/redirect}")
    private String redirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOidcUser principal = (CustomOidcUser) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(principal.getUser().getId().toString(), Map.of("Role", "User"));
        HttpSession session = request.getSession(false);
        String redirectUri = redirectUrl;
        if(session!=null){
             redirectUri = (String) session.getAttribute("redirect_uri");
        }
        redirectUri = UriComponentsBuilder.fromHttpUrl(redirectUri).queryParam("token", token).build().encode().toUriString();
        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }

}
