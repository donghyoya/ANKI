package com.kmu.anki.backend.domain.card.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QForeignCard is a Querydsl query type for ForeignCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QForeignCard extends EntityPathBase<ForeignCard> {

    private static final long serialVersionUID = 384822242L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QForeignCard foreignCard = new QForeignCard("foreignCard");

    public final StringPath foreignMeaning = createString("foreignMeaning");

    public final StringPath foreignWord = createString("foreignWord");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QKoreanCard koreanCard;

    public final NumberPath<Long> koreanCardId = createNumber("koreanCardId", Long.class);

    public final EnumPath<com.kmu.anki.backend.domain.card.enums.LanguageCode> languageCode = createEnum("languageCode", com.kmu.anki.backend.domain.card.enums.LanguageCode.class);

    public QForeignCard(String variable) {
        this(ForeignCard.class, forVariable(variable), INITS);
    }

    public QForeignCard(Path<? extends ForeignCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QForeignCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QForeignCard(PathMetadata metadata, PathInits inits) {
        this(ForeignCard.class, metadata, inits);
    }

    public QForeignCard(Class<? extends ForeignCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.koreanCard = inits.isInitialized("koreanCard") ? new QKoreanCard(forProperty("koreanCard")) : null;
    }

}

