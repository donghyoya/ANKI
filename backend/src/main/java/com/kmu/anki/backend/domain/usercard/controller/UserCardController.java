package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.usercard.controller.form.StudyCardForm;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cards/study")
@RestController
public class UserCardController {
    private final UserCardService userCardService;

    @GetMapping("/{userCardId}")
    public UserCardDto getUserCards(
            @PathVariable("userCardId") Long userCardId
    ){
        return userCardService.findByUserCardId(userCardId);
    }

    @PostMapping("/{userCardId}")
    public UserCardDto putUserCards(
            @PathVariable("userCardId") Long userCardId,
            @RequestBody StudyCardForm form
    ){
        return userCardService.updateUserCard(
                userCardId,
                form.getNextStudyDate(),
                form.getLapses(),
                form.getLastReview(),
                form.getReps(),
                form.getScheduledDays(),
                form.getStability(),
                form.getState()
        );
    }
}
