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

    /* 관계 : KoreanCard */
    @ManyToOne
    @JoinColumn(name = "korean_card_id")
    private KoreanCard koreanCard;

    @Column(name = "korean_card_id", insertable = false, updatable = false)
    private Long koreanCardId;


    /* 관계 meaningGroup */
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "topic_id", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CardTopicEnums topicId;

}
