package com.kmu.anki.backend.domain.study.history.dto;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserStudyHistoryDto {
    private QueryType deckType;
    private String deckName;
    private StudyType studyType;
    private LocalDateTime studyDate;

    public static UserStudyHistoryDto of(UserStudyHistory userStudyHistory){
        QueryType queryType = userStudyHistory.getDeckType();
        String deckname = null;
        if(queryType.equals(QueryType.level)){
            deckname = userStudyHistory.getCardLevel().name();
        }else {
            deckname = userStudyHistory.getCardTopic().name();
        }
        return new UserStudyHistoryDtoBuilder()
                .deckType(queryType)
                .deckName(deckname)
                .studyType(userStudyHistory.getStudyType())
                .studyDate(userStudyHistory.getStudyDate())
                .build();
    }
}
