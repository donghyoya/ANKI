package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.auth.legacy.utils.PrincipalUtils;
import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.card.service.DeckService;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.domain.user.utils.SessionUtils;
import com.kmu.anki.backend.global.schema.BaseListReponse;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/decks")
@RequiredArgsConstructor
@RestController
public class DeckController {
    private final CardService cardService;
    private final DeckService deckService;
    private final UserOptionService userOptionService;

    @GetMapping
    public BaseListReponse<DeckDto> getDecks(
            @RequestParam("queryType") QueryType queryType,
            Authentication authentication
    ){
        Long userId = null;
        if(authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken){
            userId = Long.parseLong(authentication.getName());
        }
        List<DeckDto> decks = new ArrayList<>();
        if(queryType == QueryType.level){
            decks = deckService.readDeckByDifficulty(userId);
        }else if(queryType == QueryType.meaning){
            decks = deckService.readDeckByMeaningGroup(userId);
        }
        return BaseListReponse.of(decks);
    }

    @GetMapping("/cards")
    public BasePageResponse<CardDetailDto> getDeckCards(
            @RequestParam("queryType") QueryType queryType,
            @RequestParam("query") String query,
            @RequestParam(value = "code", required = false) LanguageCode code,
            Authentication authentication
    ){
        if(code == null){
            UserOptionDto userOptionDto = userOptionService.readOption(authentication.getName());
            code = userOptionDto.getLanguageCode();
        }
        Page<CardDetailDto> cards;
        if(queryType == QueryType.meaning){
            CardTopicEnums cardTopicEnums = CardTopicEnums.valueOf(query);
            cards = deckService.findDeckCards(code, cardTopicEnums);
        }else {
            CardLevel cardLevel = CardLevel.valueOf(query);
            cards = deckService.findDeckCards(code, cardLevel);
        }
        return BasePageResponse.of(cards);
    }
}
