package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.study.history.dto.UserStudyHistoryDto;
import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import com.kmu.anki.backend.domain.study.history.service.UserStudyHistoryService;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import com.kmu.anki.backend.global.schema.BaseListReponse;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class UserCardHistoryController {
    private final UserCardService userCardService;
    private final UserStudyHistoryService userStudyHistoryService;

    @GetMapping("/history")
    public BasePageResponse<UserStudyHistoryDto> getUserHistory(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ){
        // TODO USER정보 가져오기
        Long userId = 1L;
        Page<UserStudyHistoryDto> userStudyHistoryDtos = userStudyHistoryService.readUserHistory(userId, page, pageSize);
        return BasePageResponse.of(userStudyHistoryDtos);
    }
}
