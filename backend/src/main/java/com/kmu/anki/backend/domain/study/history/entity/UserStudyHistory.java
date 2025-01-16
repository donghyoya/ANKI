package com.kmu.anki.backend.domain.study.history.entity;

import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class UserStudyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_study_history_id")
    private Long id;

    /**
     * CardLevel 및 CardTopic의 toString으로만 제한함
     */

    @Column
    private String deckType;

    @Column
    @Enumerated(EnumType.STRING)
    private StudyType studyType;

    @Column
    private LocalDateTime studyDate;

    /* 관계 - User */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public static UserStudyHistory from(StudyType studyType, String deckType ,User user){
        return new UserStudyHistoryBuilder()
                .studyType(studyType)
                .deckType(deckType)
                .studyDate(LocalDateTime.now())
                .user(user)
                .build();
    }
}
