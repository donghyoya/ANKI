package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private CardTopicEnums topic;

    /* 관계 - CardTopic */
    @OneToMany(mappedBy = "topic")
    private List<CardTopic> cardTopic;
}
