package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;

@Getter
public class DeckDto {
    private String category;
    private LanguageCode languageCode;
    private Long cardCounts;

    public DeckDto(LanguageCode languageCode, CardDifficulty category, Long cardCounts) {
        this.category = category.toString();
        this.languageCode = languageCode;
        this.cardCounts = cardCounts;
    }

    public DeckDto(LanguageCode languageCode, CardMeaningGroup category, Long cardCounts) {
        this.category = category.toString();
        this.languageCode = languageCode;
        this.cardCounts = cardCounts;
    }

}
