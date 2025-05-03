package com.kmu.anki.backend.domain.card.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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
    private KoreanCard koreanCard;

    private void mapKoreanCard(KoreanCard koreanCard){
        this.koreanCard = koreanCard;
    }

    /* 관계 - CardTopics */
    @OneToMany(mappedBy = "koreanMeaning")
    private List<CardTopic> cardTopics;

    /* 관계 - 외국어 카드 */
    @OneToMany(mappedBy = "koreanMeaning")
    private List<ForeignCard> foreignCards;

    public void addForeignCards(ForeignCard card){
        foreignCards.add(card);
    }
}
