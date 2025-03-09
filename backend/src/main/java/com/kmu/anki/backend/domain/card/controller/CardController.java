package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.user.utils.SessionUtils;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequestMapping("/cards")
@RequiredArgsConstructor
@RestController
public class CardController {
    private final CardService cardService;

    @GetMapping("/{cardId}")
    public CardDetailDto getCard(
            @PathVariable("cardId") Long cardId,
            @RequestParam(value = "code", required = false) LanguageCode code,
            HttpServletRequest request
    ){
        code = SessionUtils.getLanaguageCode(request).orElse(code);
        return cardService.readCardDetail(cardId, code);
    }

    @GetMapping("/{cardId}/details")
    public CardDetailDto getCardDetails(
            @PathVariable("cardId") Long cardId,
            @RequestParam(value = "code", required = false) LanguageCode code,
            HttpServletRequest request
    ){
        code = SessionUtils.getLanaguageCode(request).orElse(code);
        return cardService.readCardDetail(cardId, code);
    }

    @GetMapping("/foreign-search")
    public BasePageResponse<CardDetailDto> getForeignSearch(
            @RequestParam(value = "code", required = false) LanguageCode code,
            @RequestParam("query") String query,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            HttpServletRequest request
    ){
        if(code == null){
            // code가 명시되지 않으면 userOption 사용
            code = SessionUtils.getLanaguageCode(request).orElseThrow();
        }
        return BasePageResponse.of(cardService.searchForeignCards(code, query, page-1, pageSize));
    }

    @GetMapping("/korean-search")
    public BasePageResponse<CardDetailDto> getForeignSearch(
            @RequestParam("query") String query,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam(value = "code", required = false) LanguageCode code,
            HttpServletRequest request
    ){
        if(code == null){
            // code가 명시되지 않으면 userOption 사용
            code = SessionUtils.getLanaguageCode(request).orElseThrow();
        }
        return BasePageResponse.of(cardService.searchKoreanCards(code, query, page-1, pageSize));
    }


}
