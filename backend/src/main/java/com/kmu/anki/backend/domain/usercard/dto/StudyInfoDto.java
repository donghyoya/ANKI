package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class StudyInfoDto {
    private LocalDateTime due;
    private Integer lapses;
    private LocalDateTime lastReview;
    private Integer reps;
    private Double scheduledDays;
    private Double stability;
    private Double difficulty;
    private CardState state;

    public StudyInfoDto(LocalDateTime due, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, Double difficulty, CardState state) {
        this.due = due;
        this.lapses = lapses;
        this.lastReview = lastReview;
        this.reps = reps;
        this.scheduledDays = scheduledDays;
        this.stability = stability;
        this.difficulty = difficulty;
        this.state = state;
    }

    public static StudyInfoDto of(UserCard userCard){
        return new StudyInfoDto(
            userCard.getDue(),
            userCard.getLapses(),
            userCard.getLastReview(),
            userCard.getReps(),
            userCard.getScheduledDays(),
            userCard.getStability(),
            userCard.getDifficulty(),
            userCard.getState()
        );
    }
}
