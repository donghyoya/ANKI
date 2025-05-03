package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
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

    @Column
    private String homographNumber; // 동형어 번호

    /* 한국어 의미 관련 */
    @Enumerated(EnumType.STRING)
    @Column
    private CardLevel level;


    /* 관계 - 유저 카드 */
    @OneToMany(mappedBy = "koreanCard")
    private List<UserCard> userCards;

    public void addUserCards(UserCard card){
        userCards.add(card);
    }

}
