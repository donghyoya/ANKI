package com.kmu.anki.backend.domain.auth.legacy.controller;

import com.kmu.anki.backend.domain.auth.legacy.service.CustomOAuth2UserService;
import com.kmu.anki.backend.domain.auth.legacy.service.OAuth2AccessTokenService;
import com.kmu.anki.backend.domain.user.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/api/auth")
@Controller
public class AuthController {

    @Autowired
    private OAuth2AccessTokenService oAuth2AccessTokenService;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;


    @GetMapping({"", "/", "/login.do"})
    public String loginPage() {
        System.out.println("running login page =======" );
        return "login"; // login.html 반환
    }
    @PostMapping("/login.do")
    public ResponseEntity<Map<String, Object>> loginProc(){
        Map<String, Object> rtMap = new HashMap<>();
        System.out.println("running login process  " );

        return new ResponseEntity<>(rtMap, HttpStatus.OK);
    }

    @GetMapping("/oauth2/google")
    public ResponseEntity<Map<String, Object>> handleOAuth2(
            HttpServletRequest req, HttpServletResponse rep,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "scope" ,required = false) String scope,
            @RequestParam(value = "authuser", required = false) String authUser,
            @RequestParam(value = "prompt", required = false) String prompt) throws IOException {

        OAuth2AccessToken accessToken = oAuth2AccessTokenService.getAccessToken(code);

        // 2. OAuth2UserRequest 객체 생성
        ClientRegistration clientRegistration = oAuth2AccessTokenService.getGoogleClientRegistration();

        OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, accessToken);

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);


        //세션 추가
        securitySession(req, rep, oAuth2User);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 세션에 필요 정보 추가
        SessionUtils.setUserOptions(
                req,
                oAuth2User.getAttribute("todayStudyWords"),
                oAuth2User.getAttribute("todayReviewWords"),
                oAuth2User.getAttribute("languageCode")
        );

        log.debug("attributes.toString() = " + attributes.toString());
        System.out.println("attributes.toString() = " + attributes.toString());

        String referer = req.getHeader("Referer");


        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/page/oauth2/google")
    public ResponseEntity<Map<String, Object>> handleOAuth2Page(
            HttpServletRequest req, HttpServletResponse rep,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "scope" ,required = false) String scope,
            @RequestParam(value = "authuser", required = false) String authUser,
            @RequestParam(value = "prompt", required = false) String prompt) throws IOException {

        OAuth2AccessToken accessToken = oAuth2AccessTokenService.getAccessToken(code);

        // 2. OAuth2UserRequest 객체 생성
        ClientRegistration clientRegistration = oAuth2AccessTokenService.getGoogleClientRegistration();

        OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, accessToken);

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        //세션 추가
        securitySession(req, rep, oAuth2User);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.debug("attributes.toString() = " + attributes.toString());
//        System.out.println("attributes.toString() = " + attributes.toString());

        String referer = req.getHeader("Referer");

        if(oAuth2User != null){
            rep.sendRedirect("/swagger-ui/index.html");
        }

        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/checkLogin.do")
    public ResponseEntity<Map<String, Object>> checkLoginStatus(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        if (authentication == null || !authentication.isAuthenticated()) {
            response.put("isAuthenticated", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        response.put("isAuthenticated", true);
        response.put("user", authentication.getPrincipal());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout.do")
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest req, HttpServletResponse rep){

        SecurityContextHolder.clearContext();

        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "로그아웃 성공");

        return ResponseEntity.ok(responseBody);
    }

    private static void securitySession(HttpServletRequest req, HttpServletResponse rep, OAuth2User oAuth2User) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                oAuth2User,
                null,
                oAuth2User.getAuthorities()
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        HttpSessionSecurityContextRepository repo = new HttpSessionSecurityContextRepository();
        repo.saveContext(context, req, rep);
    }

}
