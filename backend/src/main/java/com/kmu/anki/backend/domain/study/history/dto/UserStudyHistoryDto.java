package com.kmu.anki.backend.domain.study.history.dto;

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
    private String deckType;
    private StudyType studyType;
    private LocalDateTime studyDate;

    public static UserStudyHistoryDto of(UserStudyHistory userStudyHistory){
        return new UserStudyHistoryDtoBuilder()
                .deckType(userStudyHistory.getDeckType())
                .studyType(userStudyHistory.getStudyType())
                .studyDate(userStudyHistory.getStudyDate())
                .build();
    }
}
