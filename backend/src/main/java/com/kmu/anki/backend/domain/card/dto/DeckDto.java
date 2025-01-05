package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import lombok.Getter;

@Getter
public class DeckDto {
    private String category;
    private Long cardCounts;
    private Integer overdueCounts;
    private Integer maturitiyCounts;
    private Double overdueRate = 0.4;
    private Double maturitiyRate = 0.65;

    public DeckDto(CardLevel level, Long cardCounts) {
        this.category = level.toString();
        this.cardCounts = cardCounts;
    }

    public DeckDto(CardTopicEnums meaning, Long cardCounts) {
        this.category = meaning.toString();
        this.cardCounts = cardCounts;
    }

    public DeckDto(String category, Long cardCounts, Integer overdueCounts, Integer maturitiyCounts) {
        this.category = category;
        this.cardCounts = cardCounts;
        this.overdueCounts = overdueCounts;
        this.maturitiyCounts = maturitiyCounts;
        this.overdueRate = (double) (overdueCounts / cardCounts);
        this.maturitiyRate = (double) (maturitiyCounts / cardCounts);
    }
}
