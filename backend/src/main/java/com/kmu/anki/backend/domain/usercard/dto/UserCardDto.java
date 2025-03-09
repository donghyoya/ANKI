package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCardDto {
    private Long cardId;
    private String koreanWord;
    private String foreignWord;
    private CardLevel level;
    private LanguageCode languageCode;
    private Long userCardId;
    private LocalDateTime due;
    private Integer lapses;
    private LocalDateTime lastReview;
    private Integer reps;
    private Double scheduledDays;
    private Double stability;
    private CardState state;
    private Double difficulty;
    private String originalLanguage;
    private String homographNumber; // 동형어 번호
    private String partsOfSpeech; // 품사
    private String pronunciation; // 발음
    private String relatedWords; // 관련어
    private String inflection; // 활용
    private String exampleUsage; // 용례

    public UserCardDto(
            Long cardId,
            String koreanWord,
            String foreignWord,
            CardLevel level,
            LanguageCode languageCode,
            Long userCardId,
            LocalDateTime due,
            Integer lapses,
            LocalDateTime lastReview,
            Integer reps,
            Double scheduledDays,
            Double stability,
            CardState state,
            Double difficulty
    ) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.foreignWord = foreignWord;
        this.level = level;
        this.languageCode = languageCode;
        this.userCardId = userCardId;
        this.due = due;
        this.lapses = lapses;
        this.lastReview = lastReview;
        this.reps = reps;
        this.scheduledDays = scheduledDays;
        this.stability = stability;
        this.state = state;
        this.difficulty = difficulty;
    }

    public UserCardDto(Long cardId, String koreanWord, String foreignWord, CardLevel level, LanguageCode languageCode, Long userCardId, LocalDateTime due, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, CardState state, Double difficulty, String originalLanguage, String homographNumber, String partsOfSpeech, String pronunciation, String relatedWords, String inflection, String exampleUsage) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.foreignWord = foreignWord;
        this.level = level;
        this.languageCode = languageCode;
        this.userCardId = userCardId;
        this.due = due;
        this.lapses = lapses;
        this.lastReview = lastReview;
        this.reps = reps;
        this.scheduledDays = scheduledDays;
        this.stability = stability;
        this.state = state;
        this.difficulty = difficulty;
        this.originalLanguage = originalLanguage;
        this.homographNumber = homographNumber;
        this.partsOfSpeech = partsOfSpeech;
        this.pronunciation = pronunciation;
        this.relatedWords = relatedWords;
        this.inflection = inflection;
        this.exampleUsage = exampleUsage;
    }
}
