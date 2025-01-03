package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "foreign_cards")
@Entity
public class ForeignCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foreign_card_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageCode languageCode;

    @Column(columnDefinition = "TEXT")
    private String foreignWord;

    @Column(columnDefinition = "TEXT")
    private String foreignMeaning;

    /* 관계 - 한국어 카드 */
    @ManyToOne
    @JoinColumn(name = "korean_card_id")
    private KoreanCard koreanCard;

    @Column(name = "korean_card_id", insertable = false, updatable = false)
    private Long koreanCardId;

    public void mapKoreanCard(KoreanCard koreanCard){
        this.koreanCard = koreanCard;
        this.koreanCard.addForeignCards(this);
    }
}
