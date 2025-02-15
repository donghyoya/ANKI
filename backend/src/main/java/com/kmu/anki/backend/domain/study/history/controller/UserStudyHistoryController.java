package com.kmu.anki.backend.domain.study.history.controller;

import com.kmu.anki.backend.domain.auth.utils.PrincipalUtils;
import com.kmu.anki.backend.domain.study.history.dto.UserStudyHistoryDto;
import com.kmu.anki.backend.domain.study.history.service.UserStudyHistoryService;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserStudyHistoryController {
    private final UserCardService userCardService;
    private final UserStudyHistoryService userStudyHistoryService;

    @GetMapping("/decks/history")
    public BasePageResponse<UserStudyHistoryDto> getUserHistory(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            Authentication authentication
    ){
        Long userId = PrincipalUtils.extractUserId(authentication);
        Page<UserStudyHistoryDto> userStudyHistoryDtos = userStudyHistoryService.readUserHistory(userId, page-1, pageSize);
        return BasePageResponse.of(userStudyHistoryDtos);
    }

    @GetMapping("/decks/latest")
    public UserStudyHistoryDto getLatestStudy(Authentication authentication){
        Long userId = PrincipalUtils.extractUserId(authentication);
        return userStudyHistoryService.readLatestDecks(userId);
    }
}
