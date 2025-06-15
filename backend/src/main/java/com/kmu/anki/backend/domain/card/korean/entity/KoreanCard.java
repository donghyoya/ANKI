package com.kmu.anki.backend.domain.card.korean.entity;

import com.kmu.anki.backend.domain.card.entity.CardTopic;
import com.kmu.anki.backend.domain.card.foreign.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Entity
@Indexed
@Table(name = "korean_cards")
public class KoreanCard {
    @Id
    @Column(name = "korean_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericField(name = "koreanCardId", projectable = Projectable.YES)
    private Long id;

    @FullTextField(name = "koreanWord", analyzer = "korean-analysis")
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
    @Builder.Default
    private List<UserCard> userCards = new ArrayList<>();

    public void addUserCards(UserCard card){
        userCards.add(card);
    }

    /* 관계 - koreanCard */
    @OneToMany(mappedBy = "koreanCard")
    @Builder.Default
    private List<CardTopic> cardTopics = new ArrayList<>();

    public void addCardTopics(CardTopic cardTopic) {
        this.cardTopics.add(cardTopic);
    }

    /* 관계 - KoreanMeaning*/
    @OneToMany(mappedBy = "koreanCard")
    @Builder.Default
    private List<KoreanMeaning> koreanCards = new ArrayList<>();

    public void addMeanings(KoreanMeaning koreanMeaning){
        koreanCards.add(koreanMeaning);
        koreanMeaning.mapKoreanCard(this);
    }

    /* 관계 - ForeignCard */
    @OneToMany(mappedBy = "koreanCard")
    @Builder.Default
    private List<ForeignCard> foreignCards = new ArrayList<>();
}
