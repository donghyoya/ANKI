package com.kmu.anki.backend.domain.usercard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
public class DeckCheckDto {
    private List<Long> studyCardIds;
    private List<Long> reviewCardIds;

    private boolean isStudyComplete;
    private boolean isReviewComplete;

    public DeckCheckDto(List<Long> studyCardIds, List<Long> reviewCardIds) {
        this.studyCardIds = studyCardIds;
        this.reviewCardIds = reviewCardIds;
    }

    public void check(Map<Long, UserCardCheckDto> map){
        if(studyCardIds != null){
            isStudyComplete = true;
            for (Long id : studyCardIds){
                isStudyComplete &= map.get(id).check();
                if(!isStudyComplete){
                    break;
                }
            }
        }

        if(reviewCardIds != null){
            isReviewComplete = true;
            for (Long id : reviewCardIds){
                isReviewComplete &= map.get(id).check();
                if(!isReviewComplete){
                    break;
                }
            }
        }
    }
}
