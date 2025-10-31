package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCardServiceTest extends AbstractIntegrationTest {
    @Autowired
    private UserCardService userCardService;

    @Autowired
    private UserOptionService userOptionService;

    @Test
    void readStudyUserCardRepeat() {
        Long userId = 1L;
        LanguageCode code = LanguageCode.en;
        CardLevel level = CardLevel.easy;

        List<UserCardDto> userCardDtos = userCardService.readStudyUserCard(userId, code, StudyType.study,level);
        UserCardDto userCardDto = userCardDtos.get(0);

        userCardService.updateUserCard(userCardDto.getUserCardId(), LocalDateTime.now(), 1, LocalDateTime.now(), 1, 0.1d, 0.1d, 0.1d,CardState.Review.getPriority());

        List<UserCardDto> repeatUserCardDtos = userCardService.readStudyUserCard(userId, code, StudyType.study, level);

        assertEquals(userCardDtos.size(), repeatUserCardDtos.size());
        for(int i=0; i<userCardDtos.size(); i++){
            assertEquals(userCardDtos.get(i).getUserCardId(), repeatUserCardDtos.get(i).getUserCardId());
        }
    }

    @Disabled
    @Test
    @DisplayName("오늘 학습할 단어 개수를 줄이고도 제대로 작동하는지 검사")
    void readStudyUserCardDecreaseDailyCardsTest(){
        // given
        Long userId = 1L;
        CardLevel level = CardLevel.easy;
        LanguageCode code = LanguageCode.en;
        UserOptionDto userOptionDto = userOptionService.readOption(userId);

        List<UserCardDto> before = userCardService.readStudyUserCard(userId, code, StudyType.study, level);

        // when
        userOptionService.updateOption(userId, 1,1,code, 9);
        List<UserCardDto> after = userCardService.readStudyUserCard(userId, code, StudyType.study, level);

        // then
        assertEquals(4, before.size());
        assertEquals(1, after.size());
    }

    @Disabled
    @Test
    @DisplayName("오늘 학습할 단어 개수를 늘리고도 제대로 작동하는지 검사")
    void readStudyUserCardIncreaseDailyCardsTest(){
        // given
        Long userId = 1L;
        CardLevel level = CardLevel.easy;
        LanguageCode code = LanguageCode.en;
        UserOptionDto userOptionDto = userOptionService.readOption(userId);
        userOptionService.updateOption(userId, 1,1,code, 9);
        List<UserCardDto> before = userCardService.readStudyUserCard(userId, code, StudyType.study, level);

        // when
        userOptionService.updateOption(userId, 20,1,code, 9);
        List<UserCardDto> after = userCardService.readStudyUserCard(userId, code, StudyType.study, level);

        // then
        assertEquals(1, before.size());
        assertEquals(4, after.size());
    }

}