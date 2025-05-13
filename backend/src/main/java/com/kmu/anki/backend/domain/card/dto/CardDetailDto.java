package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CardDetailDto {
    /**
     * koreanCardId
     */
    private Long cardId;
    private String koreanWord; // 표제어
    private String homographNumber; // 동형어 번호
    private CardLevel level; // 난이도
    private Set<CardTopicEnums> topics; // 카드의 분류
    private List<CardMeaningWithForeign> meanings;

    public CardDetailDto(Long cardId, String koreanWord, String homographNumber, CardLevel level, Set<CardTopicEnums> topics, List<CardMeaningWithForeign> meanings) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.homographNumber = homographNumber;
        this.level = level;
        this.topics = topics;
        this.meanings = meanings;
    }

    public static CardDetailDto of(KoreanCard card, List<CardMeaningWithForeign> meanings){
        if(card == null || meanings == null){
            return null;
        }
        return new CardDetailDto(
            card.getId(),
            card.getKoreanWord(),
            card.getHomographNumber(),
            card.getLevel(),
            card.getCardTopics().stream().map(topic->topic.getTopicId()).collect(Collectors.toSet()),
            meanings
        );
    }
}
