package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardCategory;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyRequestForm;
import com.kmu.anki.backend.domain.usercard.dto.UserDeckDto;
import com.kmu.anki.backend.domain.usercard.service.UserDeckService;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/decks/study")
@RestController
public class UserDeckController {
    private final UserDeckService userDeckService;

    @PostMapping
    public UserDeckDto createStudyDeck(
            @RequestBody StudyRequestForm form
    ){
        // TODO userId 조치하기
        UserDeckDto response = userDeckService.study(form.getLanguageCode(), form.getDifficulty(), 1L);
        return response;
    }
}
