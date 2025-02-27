package com.kmu.anki.backend.domain.user.controller;

import com.kmu.anki.backend.domain.auth.utils.PrincipalUtils;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.domain.user.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
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
    public UserOptionDto getUserOption(Authentication authentication, HttpServletRequest request){
        Long id = PrincipalUtils.extractUserId(authentication);

        UserOptionDto userOptionDto = userOptionService.readOption(id);
        SessionUtils.setUserOptions(
                request,
                userOptionDto.getTodayStudyWords(),
                userOptionDto.getTodayReviewWords(),
                userOptionDto.getLanguageCode()
        );
        return userOptionDto;
    }

    @PostMapping
    public UserOptionDto putUserOption(
            @RequestBody UserOptionDto form, Authentication authentication, HttpServletRequest request
    ){
        Long id = PrincipalUtils.extractUserId(authentication);

        UserOptionDto userOptionDto = userOptionService.updateOption(
                id,
                form.getTodayStudyWords(),
                form.getTodayReviewWords(),
                form.getLanguageCode()
        );

        SessionUtils.setUserOptions(
                request,
                userOptionDto.getTodayStudyWords(),
                userOptionDto.getTodayReviewWords(),
                userOptionDto.getLanguageCode()
        );

        return userOptionDto;
    }
}
