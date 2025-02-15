package com.kmu.anki.backend.domain.user.controller;

import com.kmu.anki.backend.domain.auth.utils.PrincipalUtils;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user/option")
@RestController
public class UserOptionController {
    private final UserOptionService userOptionService;

    @GetMapping()
    public UserOptionDto getUserOption(HttpSession session){
        Long id = (Long) session.getAttribute("userId");
        return userOptionService.readOption(id);
    }

    @PostMapping
    public UserOptionDto putUserOption(
            @RequestBody UserOptionDto form, Authentication authentication
    ){
        Long id = PrincipalUtils.extractUserId(authentication);
        return userOptionService.updateOption(
                id,
                form.getTodayStudyWords(),
                form.getTodayReviewWords(),
                form.getLanguageCode()
        );
    }
}
