package com.kmu.anki.backend.domain.auth;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public class authController {


    @PostMapping("/google-login")
    public ResponseEntity<String> googleLoginApi(@RequestBody @Valid Social)
}
