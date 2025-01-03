package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;

@Getter
public class CardDetailDto {
    private Long cardId;
    private String koreanWord;
    private String foreignWord;
    private CardLevel level;
    private LanguageCode languageCode;
    private String headword; // 표제어
    private String homographNumber; // 동형어 번호
    private String partsOfSpeech; // 품사
    private String pronunciation; // 발음
    private String relatedWords; // 관련어
    private String inflection; // 활용
    private String exampleUsage; // 용례

    public CardDetailDto(Long cardId, String koreanWord, String foreignWord, CardLevel level, LanguageCode languageCode, String headword, String homographNumber, String partsOfSpeech, String pronunciation, String relatedWords, String inflection, String exampleUsage) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.foreignWord = foreignWord;
        this.level = level;
        this.languageCode = languageCode;
        this.headword = headword;
        this.homographNumber = homographNumber;
        this.partsOfSpeech = partsOfSpeech;
        this.pronunciation = pronunciation;
        this.relatedWords = relatedWords;
        this.inflection = inflection;
        this.exampleUsage = exampleUsage;
    }

}
