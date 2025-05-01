package com.kmu.anki.backend.domain.user.entity;

import lombok.Getter;

@Getter
public enum CardState {
    New(0),
    Learning(1),
    Review(0),
    Relearning(1);

    private Integer priority;

    CardState(Integer priority) {
        this.priority = priority;
    }
}
