package com.kmu.anki.backend.domain.usercard.controller.form;

import com.kmu.anki.backend.domain.user.entity.CardState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyCardForm {
    private LocalDateTime due;
    private Integer lapses;
    private LocalDateTime lastReview;
    private Integer reps;
    private Double scheduledDays;
    private Double stability;
    private Double difficulty;
    private Integer state;
}
