package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyCardForm;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import com.kmu.anki.backend.global.schema.BaseListReponse;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class UserCardController {
    private final UserCardService userCardService;

    @GetMapping("/{userCardId}")
    public UserCardDto getUserCards(
            @PathVariable("userCardId") Long userCardId
    ){
        return userCardService.findByUserCardId(userCardId);
    }

    @PostMapping("/{userCardId}")
    public UserCardDto putUserCards(
            @PathVariable("userCardId") Long userCardId,
            @RequestBody StudyCardForm form
    ){
        return userCardService.updateUserCard(
                userCardId,
                form.getNextStudyDate(),
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
            CardMeaningGroup cardMeaningGroup = CardMeaningGroup.valueOf(query);
            cards = userCardService.readStudyUserCard(userId, languageCode, cardMeaningGroup);
        }else {
            CardDifficulty cardDifficulty = CardDifficulty.valueOf(query);
            cards = userCardService.readStudyUserCard(userId, languageCode, cardDifficulty);
        }
        return BasePageResponse.of(cards);
    }
}
