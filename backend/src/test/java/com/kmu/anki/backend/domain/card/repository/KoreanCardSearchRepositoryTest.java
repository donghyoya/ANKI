package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class KoreanCardSearchRepositoryTest extends AbstractIntegrationTest {
    @Autowired private KoreanCardSearchRepository koreanCardSearchRepository;

    @Test
    void searchKoreanCardIdByKoreanWord() {
        Page<Long> ids = koreanCardSearchRepository.searchKoreanCardIdByKoreanWord("표제어", PageRequest.of(0, 20));
        assertEquals(20, ids.getSize());
    }
}