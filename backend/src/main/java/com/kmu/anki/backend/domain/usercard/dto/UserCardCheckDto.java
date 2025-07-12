package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.user.entity.CardState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCardCheckDto {
    private Long userId;
    private CardState cardState;
    private LocalDateTime due;
}
