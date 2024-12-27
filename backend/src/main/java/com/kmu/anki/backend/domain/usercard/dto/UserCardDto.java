package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.card.entity.Card;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCardDto {
    private Long cardId;
    private String koreanWord;
    private String foreignWord;
    private CardDifficulty difficulty;
    private LanguageCode languageCode;
    private Long userCardId;
    private Integer score;
    private LocalDateTime nextStudyDate;
    private Integer lapses;
    private LocalDateTime lastReview;
    private Integer reps;
    private Double scheduledDays;
    private Double stability;
    private CardState state;

    public UserCardDto(
            Long cardId,
            String koreanWord,
            String foreignWord,
            CardDifficulty difficulty,
            LanguageCode languageCode,
            Long userCardId,
            Integer score,
            LocalDateTime nextStudyDate,
            Integer lapses,
            LocalDateTime lastReview,
            Integer reps,
            Double scheduledDays,
            Double stability,
            CardState state
    ) {
        this.cardId = cardId;
        this.koreanWord = koreanWord;
        this.foreignWord = foreignWord;
        this.difficulty = difficulty;
        this.languageCode = languageCode;
        this.userCardId = userCardId;
        this.score = score;
        this.nextStudyDate = nextStudyDate;
        this.lapses = lapses;
        this.lastReview = lastReview;
        this.reps = reps;
        this.scheduledDays = scheduledDays;
        this.stability = stability;
        this.state = state;
    }

    public UserCardDto(UserCard uc, Card c){
        this.cardId = c.getId();
        this.koreanWord = c.getKoreanWord();
        this.foreignWord = c.getForeignWord();
        this.difficulty = c.getDifficulty();
        this.languageCode = c.getLanguageCode();
        this.userCardId = uc.getId();
        this.score = uc.getScore();
        this.nextStudyDate = uc.getNextStudyDate();
        this.lapses = uc.getLapses();
        this.lastReview = uc.getLastReview();
        this.reps = uc.getReps();
        this.scheduledDays = uc.getScheduledDays();
        this.stability = uc.getStability();
        this.state = uc.getState();
    }
}
