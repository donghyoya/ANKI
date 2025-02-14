package com.kmu.anki.backend.domain.card.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTopic is a Querydsl query type for Topic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTopic extends EntityPathBase<Topic> {

    private static final long serialVersionUID = 1226379661L;

    public static final QTopic topic1 = new QTopic("topic1");

    public final ListPath<CardTopic, QCardTopic> cardTopic = this.<CardTopic, QCardTopic>createList("cardTopic", CardTopic.class, QCardTopic.class, PathInits.DIRECT2);

    public final EnumPath<com.kmu.anki.backend.domain.card.enums.CardTopicEnums> topic = createEnum("topic", com.kmu.anki.backend.domain.card.enums.CardTopicEnums.class);

    public QTopic(String variable) {
        super(Topic.class, forVariable(variable));
    }

    public QTopic(Path<? extends Topic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTopic(PathMetadata metadata) {
        super(Topic.class, metadata);
    }

}

