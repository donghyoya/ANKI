package com.kmu.anki.backend.domain.card.entity;

import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Topic {
    @Id
    @Column(name = "topic_id")
    private Long id;

    @Column
    private CardTopicEnums topic;

    /* 관계 - CardTopic */
    @OneToMany(mappedBy = "topic")
    private List<CardTopic> cardTopic;
}
