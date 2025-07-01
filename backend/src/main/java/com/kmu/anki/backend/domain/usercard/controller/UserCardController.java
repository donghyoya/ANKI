package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.card.controller.option.QueryType;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.study.history.service.UserStudyHistoryService;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyCardForm;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.CardStudyDto;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import com.kmu.anki.backend.global.config.converter.factory.StringToEnumConverterFactory;
import com.kmu.anki.backend.global.schema.BaseListReponse;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class UserCardController {
    private final UserCardService userCardService;
    private final UserStudyHistoryService userStudyHistoryService;
    private final UserOptionService userOptionService;
    private final StringToEnumConverterFactory enumConverterFactory;

    @GetMapping("/{userCardId}/study")
    public CardStudyDto getCardsStudyInfo(
            @PathVariable("userCardId") Long userCardId
    ){
        return userCardService.readCardStudyInfo(userCardId);
    }

    @PostMapping("/{userCardId}/study")
    public CardStudyDto putUserCards(
            @PathVariable("userCardId") Long userCardId,
            @RequestBody StudyCardForm form
    ){
        return userCardService.updateUserCard(
                userCardId,
                form.getDue(),
                form.getLapses(),
                form.getLastReview(),
                form.getReps(),
                form.getScheduledDays(),
                form.getStability(),
                form.getDifficulty(),
                form.getState()
        );
    }

    @GetMapping("/study")
    public BaseListReponse<UserCardDto> getStudyCards(
            @RequestParam("studyType") StudyType studyType,
            @RequestParam("queryType") QueryType queryType,
            @RequestParam("query") String query,
            Authentication authentication
    ){
        Long userId = Long.parseLong(authentication.getName());
        UserOptionDto userOptionDto = userOptionService.readOption(userId);
        UserOptionDto.validate(userOptionDto);
        LanguageCode languageCode = userOptionDto.getLanguageCode();
        List<UserCardDto> cards;
        if(queryType == QueryType.meaning){
            CardTopicEnums cardTopicEnums = enumConverterFactory.convertTopic(query);
            cards = userCardService.readStudyUserCard(userId, languageCode, studyType,cardTopicEnums);
            // 최근 학습 덱을 보여주기 위해서
            userStudyHistoryService.createHistory(studyType, queryType, cardTopicEnums, userId);
        }else {
            CardLevel cardLevel = enumConverterFactory.convertLevel(query);
            cards = userCardService.readStudyUserCard(userId, languageCode, studyType, cardLevel);
            // 최근 학습 덱을 보여주기 위해서
            userStudyHistoryService.createHistory(studyType, queryType, cardLevel, userId);
        }

        return BaseListReponse.of(cards);
    }

    @GetMapping("/delete-cache")
    public Map<String, String> deleteCache(
            @RequestParam("studyType") StudyType studyType,
            @RequestParam("queryType") QueryType queryType,
            @RequestParam("query") String query,
            Authentication authentication
    ){
        Long userId = Long.parseLong(authentication.getName());
        UserOptionDto userOptionDto = userOptionService.readOption(userId);
        LanguageCode languageCode = userOptionDto.getLanguageCode();
        if(queryType == QueryType.meaning){
            CardTopicEnums cardTopicEnums = enumConverterFactory.convertTopic(query);
            userCardService.deleteCache(userId, languageCode, studyType,cardTopicEnums);
            // 최근 학습 덱을 보여주기 위해서
        }else {
            CardLevel cardLevel = enumConverterFactory.convertLevel(query);
            userCardService.deleteCache(userId, languageCode, studyType, cardLevel);
        }
        return Map.of("message", "cache-deleted");
    }



}
