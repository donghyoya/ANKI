package com.kmu.anki.backend.domain.user.entity;

import lombok.Getter;

@Getter
public enum CardState {
    New(0),
    Learning(1),
    Review(3),
    Relearning(4);

    private Integer priority;

    public static CardState of(Integer priority){
        if(priority == 0){
            return CardState.New;
        }else if(priority == 1){
            return CardState.Learning;
        }else if(priority == 2){
            return CardState.Review;
        }else if(priority == 3){
            return CardState.Relearning;
        }else {
            return null;
        }
    }

    CardState(Integer priority) {
        this.priority = priority;
    }
}
