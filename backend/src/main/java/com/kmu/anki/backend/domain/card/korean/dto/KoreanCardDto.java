package com.kmu.anki.backend.domain.card.korean.dto;

import com.kmu.anki.backend.domain.card.korean.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class KoreanCardDto {
    private Long cardId;
    private String koreanWord; // 표제어
    private String homographNumber; // 동형어 번호
    private CardLevel level; // 난이도
    private Set<CardTopicEnums> topics; // 카드의 분류

    public KoreanCardDto(Long cardId, String koreanWord, String homographNumber, CardLevel level, Set<CardTopicEnums> topics) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.homographNumber = homographNumber;
        this.level = level;
        this.topics = topics;
    }

    public static KoreanCardDto of(KoreanCard card){
        if(card == null){
            return null;
        }
        return new KoreanCardDto(
                card.getId(),
                card.getKoreanWord(),
                card.getHomographNumber(),
                card.getLevel(),
                card.getCardTopics().stream().map(topic->topic.getTopicId()).collect(Collectors.toSet())
        );
    }

}
