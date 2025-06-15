package com.kmu.anki.backend.domain.study.history.entity;

import com.kmu.anki.backend.domain.card.controller.option.QueryType;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
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
    @Enumerated(EnumType.STRING)
    private QueryType deckType;

    @Column
    @Enumerated(EnumType.STRING)
    private CardLevel cardLevel;

    @Column
    @Enumerated(EnumType.STRING)
    private CardTopicEnums cardTopic;

    @Column
    @Enumerated(EnumType.STRING)
    private StudyType studyType;

    @Column
    private LocalDateTime studyDate;

    /* 관계 - User */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public static UserStudyHistory from(StudyType studyType, QueryType deckType, CardLevel cardLevel, CardTopicEnums cardTopic,User user){
        return new UserStudyHistoryBuilder()
                .studyType(studyType)
                .deckType(deckType)
                .cardLevel(cardLevel)
                .cardTopic(cardTopic)
                .studyDate(LocalDateTime.now())
                .user(user)
                .build();
    }
}
