package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CardQueryRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private CardQueryRepository cardQueryRepository;

    @Test
    @DisplayName("[repository] card: 카드 id로 cardDetailDto 쿼리")
    void findDetailById() {
        Optional<CardDetailDto> opt = cardQueryRepository.findDetailById(1L, LanguageCode.en);
        assertTrue(opt.isPresent(), "쿼리의 결과가 null이 아님을 확인");

        CardDetailDto cardDetailDto = opt.get();
        assertEquals(2, cardDetailDto.getTopics().size(), "2개의 cardTopic이 모두 포함되는 지 확인");
        assertEquals(2, cardDetailDto.getMeanings().size(), "2개의 의미가 모두 포함되는 지 확인");
        assertNotNull(cardDetailDto.getMeanings().getFirst().getForeignWord(), "외국어가 null이 아닌지 확인");
    }
}