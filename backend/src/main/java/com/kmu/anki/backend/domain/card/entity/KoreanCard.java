package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
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
    private CardLevel level;

    /* TODO 제거할 것 */
    @Enumerated(EnumType.STRING)
    @Column
    private CardTopicEnums meaningGroup;

    /* 한국어 의미 관련 */

    @Column
    private String originalLanguage; // 원어

    @Column
    private String homographNumber; // 동형어 번호

    @Column
    private String partsOfSpeech; // 품사

    @Column
    private String pronunciation; // 발음

    @Column(columnDefinition = "TEXT")
    private String relatedWords; // 관련어

    @Column
    private String inflection; // 활용

    @Column(columnDefinition = "TEXT")
    private String exampleUsage; // 용례

    /* 관계 - 카드 토픽 */
    @OneToMany(mappedBy = "koreanCard")
    private List<CardTopic> cardTopics;

    /* 관계 - 외국어 카드 */

    @OneToMany(mappedBy = "koreanCard")
    private List<ForeignCard> foreignCards;

    public void addForeignCards(ForeignCard card){
        foreignCards.add(card);
    }

    /* 관계 - 유저 카드 */

    @OneToMany(mappedBy = "koreanCard")
    private List<UserCard> userCards;

    public void addUserCards(UserCard card){
        userCards.add(card);
    }

}
