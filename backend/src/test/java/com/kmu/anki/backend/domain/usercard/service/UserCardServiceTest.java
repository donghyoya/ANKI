package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCardServiceTest extends AbstractIntegrationTest {
    @Autowired
    private UserCardService userCardService;

    @Disabled
    @Test
    void readStudyUserCardRepeat() {
        Long userId = 1L;
        LanguageCode code = LanguageCode.en;
        CardLevel level = CardLevel.easy;

        List<UserCardDto> userCardDtos = userCardService.readStudyUserCard(userId, code, StudyType.study,level);
        UserCardDto userCardDto = userCardDtos.get(0);

        userCardService.updateUserCard(userCardDto.getUserCardId(), LocalDateTime.now(), 1, LocalDateTime.now(), 1, 0.1d, 0.1d, 0.1d,CardState.Review.getPriority());

        List<UserCardDto> repeatUserCardDtos = userCardService.readStudyUserCard(userId, code, StudyType.study, level);

        assertEquals(userCardDtos, repeatUserCardDtos);
    }

}