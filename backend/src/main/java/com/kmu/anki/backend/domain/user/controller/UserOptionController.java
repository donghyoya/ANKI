package com.kmu.anki.backend.domain.user.controller;

import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user/option")
@RestController
public class UserOptionController {
    private final UserOptionService userOptionService;

    @GetMapping()
    public UserOptionDto getUserOption(){
        // TODO 세션에서 user정보 가져오기
        Long id = 1L;
        return userOptionService.readOption(id);
    }

    @PostMapping
    public UserOptionDto putUserOption(
            @RequestBody UserOptionDto form
    ){
        return userOptionService.updateOption(
                form.getId(),
                form.getTodayStudyWords(),
                form.getTodayReviewWords(),
                form.getLanguageCode()
        );
    }
}
