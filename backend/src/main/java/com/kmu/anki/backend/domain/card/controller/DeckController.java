package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.card.service.DeckService;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cards")
    public BasePageResponse<CardDto> getDeckCards(
            @RequestParam("languageCode") LanguageCode languageCode,
            @RequestParam("queryType") QueryType queryType,
            @RequestParam("query") String query
    ){
        Page<CardDto> cards;
        if(queryType == QueryType.meaning){
            CardMeaningGroup cardMeaningGroup = CardMeaningGroup.valueOf(query);
            cards = deckService.findDeckCards(languageCode, cardMeaningGroup);
        }else {
            CardDifficulty cardDifficulty = CardDifficulty.valueOf(query);
            cards = deckService.findDeckCards(languageCode, cardDifficulty);
        }
        return BasePageResponse.of(cards);
    }
}
