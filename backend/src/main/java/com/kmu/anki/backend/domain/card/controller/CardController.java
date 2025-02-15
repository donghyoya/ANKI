package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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

    @GetMapping("/{cardId}/details")
    public CardDetailDto getCardDetails(
            @PathVariable("cardId") Long cardId
    ){
        // TODO lanagaugeCode 추출
        LanguageCode code = LanguageCode.en;
        return cardService.readCardDetail(cardId, code);
    }

    @GetMapping("/foreign-search")
    public BasePageResponse<CardDetailDto> getForeignSearch(
            @RequestParam("code") LanguageCode code,
            @RequestParam("query") String query,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ){
        return BasePageResponse.of(cardService.searchForeignCards(code, query, page-1, pageSize));
    }

    @GetMapping("/korean-search")
    public BasePageResponse<CardDetailDto> getForeignSearch(
            @RequestParam("query") String query,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ){
        // TODO 유저 정보
        LanguageCode code = LanguageCode.en;
        return BasePageResponse.of(cardService.searchKoreanCards(code, query, page-1, pageSize));
    }


}
