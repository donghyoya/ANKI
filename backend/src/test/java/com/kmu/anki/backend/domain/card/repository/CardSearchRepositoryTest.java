package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.repository.search.CardSearchRepository;
import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class CardSearchRepositoryTest extends AbstractIntegrationTest {
    @Autowired private CardSearchRepository cardSearchRepository;

    @Disabled
    @Test
    void searchKoreanCardByKoreanWord() {
        Page<KoreanCardDto> koreanCards = cardSearchRepository.searchKoreanCardByKoreanWord("표제어", PageRequest.of(0, 20));

        assertEquals(20, koreanCards.getSize());
        assertEquals(50, koreanCards.getTotalElements());
    }
}