package com.kmu.anki.backend.security.auth.test.controller;

import com.kmu.anki.backend.security.auth.test.service.TestAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthenticationTestController {
    private final TestAuthenticationService testAuthenticationService;

    @GetMapping("/auth-test")
    public Map<String, String> authTest(Authentication authentication) {
        String username = authentication.getName();
        return Map.of("username", username);
    }

    @GetMapping("/get-test-token")
    public Map<String, String> getTestToken(){
        String testToken = testAuthenticationService.getTestToken();
        return Map.of("token", testToken);
    }

}
