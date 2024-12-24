package com.kmu.anki.backend.domain.user.entity;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.domain.usercard.entity.UserDeck;
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

    @Column
    private LanguageCode languageCode;

    public void update(Integer todayStudyWords, Integer todayReviewWords,LanguageCode languageCode){
        this.todayStudyWords = todayStudyWords;
        this.todayReviewWords = todayReviewWords;
        this.languageCode = languageCode;
    }

    /* 관계 : 유저덱 */
    @OneToMany(mappedBy = "user")
    private List<UserDeck> decks = new ArrayList<>();

    public void addDeck(UserDeck deck){
        decks.add(deck);
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
