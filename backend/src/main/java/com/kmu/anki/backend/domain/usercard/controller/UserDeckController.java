package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardCategory;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.service.UserDeckService;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/deck/study")
@RestController
public class UserDeckController {
    private final UserDeckService userDeckService;

    @PostMapping
    public DeckDto createStudyDeck(
            @RequestParam("languageCode") LanguageCode languageCode,
            @RequestParam("difficulty") CardDifficulty difficulty
    ){
        userDeckService.study(languageCode, difficulty, 1L);
        return null;
    }
}
