package com.kmu.anki.backend.domain.user.entity;

import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.domain.usercard.entity.UserDeck;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    /* 관계 : 유저덱 */
    @OneToMany(mappedBy = "user")
    private List<UserDeck> decks = new ArrayList<>();

    public void addDeck(UserDeck deck){
        decks.add(deck);
    }




}
