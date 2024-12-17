package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.card.service.DeckService;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/decks")
@RequiredArgsConstructor
@RestController
public class DeckController {
    private final CardService cardService;
    private final DeckService deckService;

    @GetMapping
    public BasePageResponse<DeckDto> getDecks(
            @RequestParam("languageCode") LanguageCode languageCode,
            @RequestParam("queryType") QueryType queryType
    ){
        Page<DeckDto> decks = deckService.findAll(languageCode, queryType);
        return BasePageResponse.of(decks);
    }
}
