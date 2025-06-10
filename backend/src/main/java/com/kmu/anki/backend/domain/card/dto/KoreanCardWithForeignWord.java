package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.entity.CardTopic;
import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class KoreanCardWithForeignWord {
    private Long cardId;
    private String koreanWord; // 표제어
    private String homographNumber; // 동형어 번호
    private CardLevel level; // 난이도
    private Set<CardTopicEnums> topics; // 카드의 분류
    private List<String> foreignWords;

    public KoreanCardWithForeignWord(Long cardId, String koreanWord, String homographNumber, CardLevel level, Set<CardTopicEnums> topics, List<String> foreignWords) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.homographNumber = homographNumber;
        this.level = level;
        this.topics = topics;
        this.foreignWords = foreignWords;
    }

    public static KoreanCardWithForeignWord of(KoreanCard card){
        if(card == null){
            return null;
        }
        return new KoreanCardWithForeignWord(
                card.getId(),
                card.getKoreanWord(),
                card.getHomographNumber(),
                card.getLevel(),
                card.getCardTopics().stream().map(CardTopic::getTopicId).collect(Collectors.toSet()),
                card.getForeignCards().stream().map(ForeignCard::getForeignWord).collect(Collectors.toList())
        );
    }

}
