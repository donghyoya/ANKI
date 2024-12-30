package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
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

    @Enumerated(EnumType.STRING)
    @Column
    private CardMeaningGroup meaningGroup;

    /* 한국어 의미 관련 */

    @Column
    private String headword; // 표제어

    @Column
    private String homographNumber; // 동형어 번호

    @Column
    private String partsOfSpeech; // 품사

    @Column
    private String pronunciation; // 발음

    @Column
    private String relatedWords; // 관련어

    @Column
    private String inflection; // 활용

    @Column
    private String exampleUsage; // 용례

    /* 관계 - 외국어 카드 */

    @OneToMany(mappedBy = "koreanCard")
    List<ForeignCard> foreignCards;

    public void addForeignCards(ForeignCard card){
        foreignCards.add(card);
    }

    /* 관계 - 외국어 카드 */

    @OneToMany(mappedBy = "koreanCard")
    List<UserCard> userCards;

    public void addUserCards(UserCard card){
        userCards.add(card);
    }

}
