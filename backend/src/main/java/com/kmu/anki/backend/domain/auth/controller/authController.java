package com.kmu.anki.backend.domain.auth.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/auth")
@Controller
public class authController {

    @GetMapping({"", "/", "/login.do"})
    public String loginPage() {
        return "login"; // login.html 반환
    }

    @GetMapping("/oauth2/success/google")
    public String handleOAuth2Success(@AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println("oAuth2User = " + oAuth2User.getAttributes());
        return "redirect:/swagger-ui/index.html";
    }
}
