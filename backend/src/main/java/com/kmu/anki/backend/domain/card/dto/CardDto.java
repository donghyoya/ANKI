package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.foreign.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;

@Getter
public class CardDto {
    private Long cardId;
    private String koreanWord;
    private String foreignWord;
    private CardLevel level;
    private LanguageCode languageCode;

    public CardDto(Long cardId, String koreanWord, String foreignWord, CardLevel level, LanguageCode languageCode) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.foreignWord = foreignWord;
        this.level = level;
        this.languageCode = languageCode;
    }

    /* TODO */

    public static CardDto of(ForeignCard foreignCard){
        return new CardDto(
                foreignCard.getKoreanMeaning().getId(),
                foreignCard.getKoreanMeaning().getKoreanCard().getKoreanWord(),
                foreignCard.getForeignWord(),
                foreignCard.getKoreanMeaning().getKoreanCard().getLevel(),
                foreignCard.getLanguageCode()
        );
    }
}
