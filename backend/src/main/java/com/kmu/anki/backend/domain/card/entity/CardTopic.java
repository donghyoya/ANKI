package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import jakarta.persistence.*;

@Entity
@Table(name = "card_topics")
public class CardTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_topic_id")
    private Long id;

    /* 관계 : KoreanMeaning */
    @ManyToOne
    @JoinColumn(name = "korean_meaning_id")
    private KoreanMeaning koreanMeaning;

    @Column(name = "korean_meaning_id", insertable = false, updatable = false)
    private Long koreanMeaningId;


    /* 관계 meaningGroup */
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "topic_id", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CardTopicEnums topicId;

}
