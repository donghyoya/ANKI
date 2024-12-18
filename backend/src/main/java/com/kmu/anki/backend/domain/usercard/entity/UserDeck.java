package com.kmu.anki.backend.domain.usercard.entity;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
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
}
