package com.kmu.anki.backend.domain.card.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCardTopic is a Querydsl query type for CardTopic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardTopic extends EntityPathBase<CardTopic> {

    private static final long serialVersionUID = -441142947L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardTopic cardTopic = new QCardTopic("cardTopic");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QKoreanCard koreanCard;

    public final NumberPath<Long> koreanCardId = createNumber("koreanCardId", Long.class);

    public final QTopic topic;

    public final EnumPath<com.kmu.anki.backend.domain.card.enums.CardTopicEnums> topicId = createEnum("topicId", com.kmu.anki.backend.domain.card.enums.CardTopicEnums.class);

    public QCardTopic(String variable) {
        this(CardTopic.class, forVariable(variable), INITS);
    }

    public QCardTopic(Path<? extends CardTopic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCardTopic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCardTopic(PathMetadata metadata, PathInits inits) {
        this(CardTopic.class, metadata, inits);
    }

    public QCardTopic(Class<? extends CardTopic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.koreanCard = inits.isInitialized("koreanCard") ? new QKoreanCard(forProperty("koreanCard")) : null;
        this.topic = inits.isInitialized("topic") ? new QTopic(forProperty("topic")) : null;
    }

}

