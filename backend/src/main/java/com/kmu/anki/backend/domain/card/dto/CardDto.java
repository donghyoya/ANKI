package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.entity.Card;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;

@Getter
public class CardDto {
    private Long cardId;
    private String koreanWord;
    private String foerienWord;
    private CardDifficulty difficulty;
    private LanguageCode languageCode;

    public CardDto(Long cardId, String koreanWord, String foerienWord, CardDifficulty difficulty, LanguageCode languageCode) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.foerienWord = foerienWord;
        this.difficulty = difficulty;
        this.languageCode = languageCode;
    }

    public static CardDto of(Card card){
        return new CardDto(
                card.getId(),
                card.getKoreanWord(),
                card.getForeignWord(),
                card.getDifficulty(),
                card.getLanguageCode()
        );
    }
}
