package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.usercard.controller.form.StudyCardForm;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user/cards")
@RestController
public class UserCardController {
    private final UserCardService userCardService;

    @GetMapping("/{id}")
    public UserCardDto getUserCards(
            @PathVariable("id") Long id
    ){
        return userCardService.findByUserCardId(id);
    }

    @PostMapping("/{id}")
    public UserCardDto putUserCards(
            @PathVariable("id") Long id,
            @RequestBody StudyCardForm form
    ){
        return userCardService.updateUserCard(id, form.getScore(), form.getNextStudyDate());
    }
}
