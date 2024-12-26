package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "korean_cards")
public class KoreanCard {
    @Id
    @Column(name = "korean_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String koreanWord;

    @Enumerated(EnumType.STRING)
    @Column
    private CardDifficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column
    private CardMeaningGroup meaningGroup;

    /* 관계 - 외국어 카드 */

    @OneToMany(mappedBy = "koreanCard")
    List<ForeignCard> foreignCards;

    public void addForeignCards(ForeignCard card){
        foreignCards.add(card);
    }

}
