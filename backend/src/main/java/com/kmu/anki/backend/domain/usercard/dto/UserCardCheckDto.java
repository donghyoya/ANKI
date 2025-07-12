package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.user.entity.CardState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCardCheckDto {
    private Long userId;
    private CardState cardState;
    private LocalDateTime due;

    public UserCardCheckDto(Long userId, CardState cardState, LocalDateTime due) {
        this.userId = userId;
        this.cardState = cardState;
        this.due = due;
    }

    public boolean check(){
        return cardState != CardState.New && cardState != CardState.Review;
    }
}
