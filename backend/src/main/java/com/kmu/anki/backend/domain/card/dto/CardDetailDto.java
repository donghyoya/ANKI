package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CardDetailDto {
    private Long cardId;
    private String koreanWord;
    private String foreignWord;
    private CardDifficulty difficulty;
    private LanguageCode languageCode;
    private String headword; // 표제어
    private String homographNumber; // 동형어 번호
    private String partsOfSpeech; // 품사
    private String pronunciation; // 발음
    private String relatedWords; // 관련어
    private String inflection; // 활용
    private String exampleUsage; // 용례

    public CardDetailDto(Long cardId, String koreanWord, String foreignWord, CardDifficulty difficulty, LanguageCode languageCode, String headword, String homographNumber, String partsOfSpeech, String pronunciation, String relatedWords, String inflection, String exampleUsage) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.foreignWord = foreignWord;
        this.difficulty = difficulty;
        this.languageCode = languageCode;
        this.headword = headword;
        this.homographNumber = homographNumber;
        this.partsOfSpeech = partsOfSpeech;
        this.pronunciation = pronunciation;
        this.relatedWords = relatedWords;
        this.inflection = inflection;
        this.exampleUsage = exampleUsage;
    }

    public static class Builder{
        private Long cardId;
        private String koreanWord;
        private String foreignWord;
        private CardDifficulty difficulty;
        private LanguageCode languageCode;
        private String headword; // 표제어
        private String homographNumber; // 동형어 번호
        private String partsOfSpeech; // 품사
        private String pronunciation; // 발음
        private String relatedWords; // 관련어
        private String inflection; // 활용
        private String exampleUsage; // 용례

        public CardDetailDto build(){
            return new CardDetailDto(
                    cardId,
                    koreanWord,
                    foreignWord,
                    difficulty,
                    languageCode,
                    headword,
                    homographNumber,
                    partsOfSpeech,
                    pronunciation,
                    relatedWords,
                    inflection,
                    exampleUsage
            );
        }

        public Builder cardId(Long cardId) {
            this.cardId = cardId;
            return this;
        }

        public Builder koreanWord(String koreanWord) {
            this.koreanWord = koreanWord;
            return this;
        }

        public Builder foreignWord(String foreignWord) {
            this.foreignWord = foreignWord;
            return this;
        }

        public Builder difficulty(CardDifficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder languageCode(LanguageCode languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public Builder headword(String headword) {
            this.headword = headword;
            return this;
        }

        public Builder homographNumber(String homographNumber) {
            this.homographNumber = homographNumber;
            return this;
        }

        public Builder partsOfSpeech(String partsOfSpeech) {
            this.partsOfSpeech = partsOfSpeech;
            return this;
        }

        public Builder pronunciation(String pronunciation) {
            this.pronunciation = pronunciation;
            return this;
        }

        public Builder relatedWords(String relatedWords) {
            this.relatedWords = relatedWords;
            return this;
        }

        public Builder inflection(String inflection) {
            this.inflection = inflection;
            return this;
        }

        public Builder exampleUsage(String exampleUsage) {
            this.exampleUsage = exampleUsage;
            return this;
        }
    }
}
