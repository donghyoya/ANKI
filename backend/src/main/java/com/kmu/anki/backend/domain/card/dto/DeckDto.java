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
    private Double overdueRate = 0.4;
    private Double maturitiyRate = 0.65;

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
