package com.kmu.anki.backend.domain.card.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "korean_meanings")
public class KoreanMeaning {
    @Id
    @GeneratedValue
    @Column(name = "korean_meaning_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String exampleUsage; // 용례

    @Column
    private String originalLanguage; // 원어

    @Column
    private String partsOfSpeech; // 품사

    @Column
    private String pronunciation; // 발음

    @Column(columnDefinition = "TEXT")
    private String relatedWords; // 관련어

    @Column
    private String inflection; // 활용

    /* 관계 - KoreanCard */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korean_card_id")
    private KoreanCard koreanCard;

    @Column(name = "korean_card_id", updatable = false, insertable = false)
    private Long koreanCardId;

    public void mapKoreanCard(KoreanCard koreanCard){
        this.koreanCard = koreanCard;
    }

    /* 관계 - 외국어 카드 */
    @OneToMany(mappedBy = "koreanMeaning")
    @Builder.Default
    private List<ForeignCard> foreignCards = new ArrayList<>();

    public void addForeignCards(ForeignCard card){
        foreignCards.add(card);
        card.mapKoreanMeaning(this);
    }
}
