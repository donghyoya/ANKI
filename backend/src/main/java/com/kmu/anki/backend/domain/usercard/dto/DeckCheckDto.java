package com.kmu.anki.backend.domain.usercard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class DeckCheckDto {
    private List<Long> studyCardIds;
    private List<Long> reviewCardIds;

    public DeckCheckDto(List<Long> studyCardIds, List<Long> reviewCardIds) {
        this.studyCardIds = studyCardIds;
        this.reviewCardIds = reviewCardIds;
    }
}
