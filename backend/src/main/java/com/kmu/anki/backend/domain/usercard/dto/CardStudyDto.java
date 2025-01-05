package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardStudyDto {
    private Long cardId;
    private LocalDateTime nextStudyDate;
    private Integer lapses;
    private LocalDateTime lastReview;
    private Integer reps;
    private Double scheduledDays;
    private Double stability;
    private CardState state;

    public CardStudyDto(Long cardId, LocalDateTime nextStudyDate, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, CardState state) {
        this.cardId = cardId;
        this.nextStudyDate = nextStudyDate;
        this.lapses = lapses == null ? 0 : lapses;
        this.lastReview = lastReview;
        this.reps = reps;
        this.scheduledDays = scheduledDays;
        this.stability = stability;
        this.state = state;
    }

    public static CardStudyDto of(UserCard userCard){
        return new CardStudyDto(
                userCard.getKoreanCardId(),
                userCard.getDue(),
                userCard.getLapses(),
                userCard.getLastReview(),
                userCard.getReps(),
                userCard.getScheduledDays(),
                userCard.getStability(),
                userCard.getState()
        );
    }
}
