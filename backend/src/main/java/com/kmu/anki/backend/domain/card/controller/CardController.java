package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardService;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cards")
@RequiredArgsConstructor
@RestController
public class CardController {
    private final CardService cardService;
    private final UserOptionService userOptionService;

    @GetMapping("/{cardId}")
    public CardDetailDto getCard(
            @PathVariable("cardId") Long cardId,
            @RequestParam(value = "code", required = false) LanguageCode code,
            Authentication authentication
    ){
        if(code == null){
            UserOptionDto userOptionDto = userOptionService.readOption(authentication.getName());
            code = userOptionDto.getLanguageCode();
        }
        return cardService.readCardDetail(cardId, code);
    }

    @GetMapping("/{cardId}/details")
    public CardDetailDto getCardDetails(
            @PathVariable("cardId") Long cardId,
            @RequestParam(value = "code", required = false) LanguageCode code,
            Authentication authentication
    ){
        if(code == null){
            UserOptionDto userOptionDto = userOptionService.readOption(authentication.getName());
            code = userOptionDto.getLanguageCode();
        }
        return cardService.readCardDetail(cardId, code);
    }
}
