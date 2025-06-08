package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.persistence.*;
import lombok.*;
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
    @GenericField(name = "foreignCardId")
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

    /* 관계 - 한국어 카드 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korean_meaning_id")
    private KoreanMeaning koreanMeaning;

    @Column(name = "korean_meaning_id", insertable = false, updatable = false)
    private Long koreanMeaningId;

    public void mapKoreanMeaning(KoreanMeaning koreanMeaning){
        this.koreanMeaning = koreanMeaning;
    }
}
