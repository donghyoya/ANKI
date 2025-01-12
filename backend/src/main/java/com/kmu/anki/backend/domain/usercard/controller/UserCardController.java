package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyCardForm;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.CardStudyDto;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class UserCardController {
    private final UserCardService userCardService;

    @GetMapping("/{cardId}/study")
    public CardStudyDto getCardsStudyInfo(
            @PathVariable("cardId") Long cardId
    ){
        return userCardService.readCardStudyInfo(cardId);
    }

    @PostMapping("/{cardId}/study")
    public CardStudyDto putUserCards(
            @PathVariable("cardId") Long cardId,
            @RequestBody StudyCardForm form
    ){
        return userCardService.updateUserCard(
                cardId,
                form.getDue(),
                form.getLapses(),
                form.getLastReview(),
                form.getReps(),
                form.getScheduledDays(),
                form.getStability(),
                form.getState()
        );
    }

    @GetMapping("/study")
    public BasePageResponse<UserCardDto> getStudyCards(
            @RequestParam("studyType") StudyType studyType,
            @RequestParam("queryType") QueryType queryType,
            @RequestParam("query") String query
    ){
        // TODO user-data 추출
        LanguageCode languageCode = LanguageCode.en;
        Long userId = 1L;
        Page<UserCardDto> cards;
        if(queryType == QueryType.meaning){
            CardTopicEnums cardTopicEnums = CardTopicEnums.valueOf(query);
            cards = userCardService.readStudyUserCard(userId, languageCode, cardTopicEnums);
        }else {
            CardLevel cardLevel = CardLevel.valueOf(query);
            cards = userCardService.readStudyUserCard(userId, languageCode, cardLevel);
        }
        return BasePageResponse.of(cards);
    }
}
