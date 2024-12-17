package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardCategory;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String koreanWord;

    @Column
    private String foreignWord;

    @Enumerated(EnumType.STRING)
    @Column
    private CardDifficulty difficulty;

    @Column
    private CardCategory category;

    @Column
    private LanguageCode languageCode;

}
