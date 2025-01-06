package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.card.service.DeckService;
import com.kmu.anki.backend.global.schema.BaseListReponse;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/decks")
@RequiredArgsConstructor
@RestController
public class DeckController {
    private final CardService cardService;
    private final DeckService deckService;

    @GetMapping
    public BaseListReponse<DeckDto> getDecks(
            @RequestParam("queryType") QueryType queryType
    ){
        // TODO user-data 추출
        Long userId = 1L;
        List<DeckDto> decks = new ArrayList<>();
        if(queryType == QueryType.level){
            decks = deckService.readDeckByDifficulty(userId);
        }else if(queryType == QueryType.meaning){
            decks = deckService.readDeckByMeaningGroup(userId);
        }
        return BaseListReponse.of(decks);
    }

    @GetMapping("/cards")
    public BasePageResponse<CardDto> getDeckCards(
            @RequestParam("queryType") QueryType queryType,
            @RequestParam("query") String query
    ){
        // TODO user-data 추출
        LanguageCode languageCode = LanguageCode.en;
        Page<CardDto> cards;
        if(queryType == QueryType.meaning){
            CardTopicEnums cardTopicEnums = CardTopicEnums.valueOf(query);
            cards = deckService.findDeckCards(languageCode, cardTopicEnums);
        }else {
            CardLevel cardLevel = CardLevel.valueOf(query);
            cards = deckService.findDeckCards(languageCode, cardLevel);
        }
        return BasePageResponse.of(cards);
    }
}
