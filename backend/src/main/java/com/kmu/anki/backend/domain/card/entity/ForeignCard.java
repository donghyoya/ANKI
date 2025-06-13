package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.*;
import lombok.*;
import org.apache.ibatis.annotations.Many;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "foreign_cards")
@Entity
@Indexed
public class ForeignCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foreign_card_id")
    @GenericField(name = "foreignCardId", projectable = Projectable.YES)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageCode languageCode;

    @Column(columnDefinition = "TEXT")
    @FullTextField(name = "foreignWord", analyzer = "foreign-analysis")
    private String foreignWord;

    @Column(columnDefinition = "TEXT")
    @FullTextField(name = "foreignMeaning", analyzer = "foreign-analysis")
    private String foreignMeaning;

    /* 관계 - 한국어 의미 카드 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korean_meaning_id")
    private KoreanMeaning koreanMeaning;

    @Column(name = "korean_meaning_id", insertable = false, updatable = false)
    private Long koreanMeaningId;

    public void mapKoreanMeaning(KoreanMeaning koreanMeaning){
        this.koreanMeaning = koreanMeaning;
    }

    /* 관계 - 한국어 카드 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korean_card_id")
    private KoreanCard koreanCard;

    @Column(name = "korean_card_id", insertable = false, updatable = false)
    @GenericField(name = "koreanCardId", projectable = Projectable.YES)
    private Long koreanCardId;
}
