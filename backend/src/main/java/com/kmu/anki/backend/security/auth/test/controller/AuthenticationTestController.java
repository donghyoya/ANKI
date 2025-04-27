package com.kmu.anki.backend.security.auth.test.controller;

import com.kmu.anki.backend.security.auth.test.service.TestAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AuthenticationTestController {
    private final TestAuthenticationService testAuthenticationService;

    @ResponseBody
    @GetMapping("/auth-test")
    public Map<String, String> authTest(Authentication authentication) {
        String username = authentication.getName();
        return Map.of("username", username);
    }

    @ResponseBody
    @GetMapping("/get-test-token")
    public Map<String, String> getTestToken(){
        return testAuthenticationService.getTestTokens();
    }

    /**
     * redirectUri를 합의보지 않은 상황이므로 사용
     * @param redirectUri
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/auth2/authorization/google")
    public String startOAuth(@RequestParam("redirect_uri") String redirectUri, HttpSession session, HttpServletRequest request) {
        session.setAttribute("redirect_uri", redirectUri);
        return "redirect:/oauth2/authorization/google";
    }
}
