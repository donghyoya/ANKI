package com.kmu.anki.backend.domain.auth.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/auth")
public class authController {

    @GetMapping({"","/", "/login.do"})
    public String loginPage() {
        return "login"; // login.html 반환
    }

    @GetMapping("/oauth2/success")
    public String handleOAuth2Success(@AuthenticationPrincipal OAuth2User oAuth2User,
                                      @RequestParam Map<String, Object> params){
        System.out.println("oAuth2User = " + oAuth2User.toString());
        System.out.println("params = " + params.toString());
        return "swagger-ui/index.html";
    }
}
