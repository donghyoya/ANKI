package com.kmu.anki.backend.domain.user.entity;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private Integer todayStudyWords;

    @Column
    private Integer todayReviewWords;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageCode languageCode;

    public void update(Integer todayStudyWords, Integer todayReviewWords,LanguageCode languageCode){
        this.todayStudyWords = todayStudyWords;
        this.todayReviewWords = todayReviewWords;
        this.languageCode = languageCode;
    }

    /* 관계 : 유저덱 */

    /**
     * 앵간하면 사용하지 말고 따로 쿼리(UserCard 기준으로 쿼리)할 것
     */
    @OneToMany(mappedBy = "user")
    private List<UserCard> cards = new ArrayList<>();

    public void addCard(UserCard card){
        cards.add(card);
    }

    /* 관계 UserStudyHistory */
    @OneToMany(mappedBy = "user")
    private List<UserStudyHistory> studyHistories = new ArrayList<>();

    public void addStudyHistory(UserStudyHistory studyHistory){
        studyHistories.add(studyHistory);
    }

    /* 생성 */

    public User(Integer todayStudyWords, Integer todayReviewWords) {
        this(todayStudyWords, todayReviewWords, LanguageCode.en);
    }

    public User(Integer todayStudyWords, Integer todayReviewWords, LanguageCode languageCode) {
        this.todayStudyWords = todayStudyWords;
        this.todayReviewWords = todayReviewWords;
        this.languageCode = languageCode;
    }

}
