package com.kmu.anki.backend.security.auth.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthenticationTestController {
    @GetMapping("/auth-test")
    public Map<String, String> authTest(Authentication authentication) {
        String username = authentication.getName();
        return Map.of("username", username);
    }

}
