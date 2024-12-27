package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cards")
@RequiredArgsConstructor
@RestController
public class CardController {
    private final CardService cardService;

    @GetMapping("/{cardId}")
    public CardDto getCard(
            @PathVariable("cardId") Long cardId
    ){
        // TODO lanagaugeCode 추출
        LanguageCode code = LanguageCode.en;
        return cardService.readCard(cardId, code);
    }

}
