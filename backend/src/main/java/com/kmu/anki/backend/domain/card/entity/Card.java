package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String koreanWord;

    @Column
    private String foreignWord;

    @Enumerated(EnumType.STRING)
    @Column
    private CardDifficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column
    private CardMeaningGroup meaningGroup;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageCode languageCode;

}
