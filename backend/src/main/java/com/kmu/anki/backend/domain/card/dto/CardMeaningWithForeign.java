package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CardMeaningWithForeign {
    // koreanMeaning 관련
    private String originalLanguage; // 원어
    private String partsOfSpeech; // 품사
    private String pronunciation; // 발음
    private String relatedWords; // 관련어
    private String inflection; // 활용
    private String exampleUsage; // 용례

    // foreign 관련
    private LanguageCode languageCode;
    private String foreignWord;
    private String foreignMeaning;

    public CardMeaningWithForeign(String originalLanguage, String partsOfSpeech, String pronunciation, String relatedWords, String inflection, String exampleUsage, LanguageCode languageCode, String foreignWord, String foreignMeaning) {
        this.originalLanguage = originalLanguage;
        this.partsOfSpeech = partsOfSpeech;
        this.pronunciation = pronunciation;
        this.relatedWords = relatedWords;
        this.inflection = inflection;
        this.exampleUsage = exampleUsage;
        this.languageCode = languageCode;
        this.foreignWord = foreignWord;
        this.foreignMeaning = foreignMeaning;
    }
}
