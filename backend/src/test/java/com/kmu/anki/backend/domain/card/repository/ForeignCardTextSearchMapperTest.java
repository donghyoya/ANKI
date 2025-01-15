package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ForeignCardTextSearchMapperTest extends AbstractIntegrationTest {

    @Autowired private ForeignCardTextSearchMapper foreignCardTextSearchMapper;

    @Test
    void searchForeignWord() {
        CardDetailDto cardDetailDto = foreignCardTextSearchMapper.searchForeignWord(LanguageCode.en.toString(), "economy");
        assertEquals(57, cardDetailDto.getCardId());

        cardDetailDto = foreignCardTextSearchMapper.searchForeignWord(LanguageCode.ja.toString(), "しゃかい");
        assertEquals(56, cardDetailDto.getCardId());
    }
}