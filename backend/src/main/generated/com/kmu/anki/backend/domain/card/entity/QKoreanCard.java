package com.kmu.anki.backend.domain.card.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKoreanCard is a Querydsl query type for KoreanCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKoreanCard extends EntityPathBase<KoreanCard> {

    private static final long serialVersionUID = -639784810L;

    public static final QKoreanCard koreanCard = new QKoreanCard("koreanCard");

    public final ListPath<CardTopic, QCardTopic> cardTopics = this.<CardTopic, QCardTopic>createList("cardTopics", CardTopic.class, QCardTopic.class, PathInits.DIRECT2);

    public final StringPath exampleUsage = createString("exampleUsage");

    public final ListPath<ForeignCard, QForeignCard> foreignCards = this.<ForeignCard, QForeignCard>createList("foreignCards", ForeignCard.class, QForeignCard.class, PathInits.DIRECT2);

    public final StringPath homographNumber = createString("homographNumber");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inflection = createString("inflection");

    public final StringPath koreanWord = createString("koreanWord");

    public final EnumPath<com.kmu.anki.backend.domain.card.enums.CardLevel> level = createEnum("level", com.kmu.anki.backend.domain.card.enums.CardLevel.class);

    public final StringPath originalLanguage = createString("originalLanguage");

    public final StringPath partsOfSpeech = createString("partsOfSpeech");

    public final StringPath pronunciation = createString("pronunciation");

    public final StringPath relatedWords = createString("relatedWords");

    public final ListPath<com.kmu.anki.backend.domain.usercard.entity.UserCard, com.kmu.anki.backend.domain.usercard.entity.QUserCard> userCards = this.<com.kmu.anki.backend.domain.usercard.entity.UserCard, com.kmu.anki.backend.domain.usercard.entity.QUserCard>createList("userCards", com.kmu.anki.backend.domain.usercard.entity.UserCard.class, com.kmu.anki.backend.domain.usercard.entity.QUserCard.class, PathInits.DIRECT2);

    public QKoreanCard(String variable) {
        super(KoreanCard.class, forVariable(variable));
    }

    public QKoreanCard(Path<? extends KoreanCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKoreanCard(PathMetadata metadata) {
        super(KoreanCard.class, metadata);
    }

}

