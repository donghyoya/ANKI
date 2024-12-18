package com.kmu.anki.backend.domain.usercard.entity;

import com.kmu.anki.backend.domain.user.entity.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_cards")
public class UserCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_card_id")
    private Long id;

    /* 관계 Deck */

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDeck deck;

    public void mapDeck(UserDeck deck){
        this.deck = deck;
        this.deck.addCard(this);
    }
}
