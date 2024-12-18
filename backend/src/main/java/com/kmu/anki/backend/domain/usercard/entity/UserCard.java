package com.kmu.anki.backend.domain.usercard.entity;

import com.kmu.anki.backend.domain.card.entity.Card;
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
    @JoinColumn(name = "user_deck_id")
    private UserDeck deck;

    @Column(name = "user_deck_id", insertable = false, updatable = false)
    private Long deckId;

    public void mapDeck(UserDeck deck){
        this.deck = deck;
        this.deck.addCard(this);
    }

    /* 관계 Card */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "card_id", insertable = false, updatable = false)
    private Long cardId;
}
