package com.kmu.anki.backend.domain.usercard.entity;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_decks")
public class UserDeck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_deck_id")
    private Long id;

    @Column
    private LanguageCode languageCode;

    @Column
    private CardDifficulty cardDifficulty;

    /* 관계 : User */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public void mapUser(User user){
        this.user = user;
        user.addDeck(this);
    }
}
