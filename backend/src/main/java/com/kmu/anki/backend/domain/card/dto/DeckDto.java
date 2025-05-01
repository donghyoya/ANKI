package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import lombok.Getter;

@Getter
public class DeckDto {
    private String category;
    private Long cardCounts;

    /**
     * 아예 본 적 없는 카드(State가 New)의 수
     */
    private Integer newCounts;

    /**
     * 학습을 했다가 due가 지난 카드(State가 Review고 due가 지남)의 수
     */
    private Integer learningCounts;

    /**
     * 최근에 입력한 Rating이 Again인 카드(State가 Learning 또는 Relearning)의 수
     */
    private Integer overdueCounts;

    /**
     * 사용자가 기억하고 있다고 추정되는 카드(State가 Review)의 수
     */
    private Integer maturityCounts;

    public DeckDto(CardLevel level, Long cardCounts) {
        this.category = level.toString();
        this.cardCounts = cardCounts;
    }

    public DeckDto(CardTopicEnums meaning, Long cardCounts) {
        this.category = meaning.toString();
        this.cardCounts = cardCounts;
    }

    public DeckDto(String category, Long cardCounts, Integer overdueCounts, Integer maturityCounts) {
        this.category = category;
        this.cardCounts = cardCounts;
        this.overdueCounts = overdueCounts;
        this.maturityCounts = maturityCounts;
    }

    public DeckDto(String category, Long cardCounts, Integer newCounts, Integer learningCounts, Integer overdueCounts, Integer maturityCounts) {
        this.category = category;
        this.cardCounts = cardCounts;
        this.newCounts = newCounts;
        this.learningCounts = learningCounts;
        this.overdueCounts = overdueCounts;
        this.maturityCounts = maturityCounts;
    }
}
