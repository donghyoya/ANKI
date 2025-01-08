package com.kmu.anki.backend.domain.usercard.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCard is a Querydsl query type for UserCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCard extends EntityPathBase<UserCard> {

    private static final long serialVersionUID = -1275262424L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCard userCard = new QUserCard("userCard");

    public final NumberPath<Double> difficulty = createNumber("difficulty", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.kmu.anki.backend.domain.card.entity.QKoreanCard koreanCard;

    public final NumberPath<Long> koreanCardId = createNumber("koreanCardId", Long.class);

    public final NumberPath<Integer> lapses = createNumber("lapses", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> lastReview = createDateTime("lastReview", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> nextStudyDate = createDateTime("nextStudyDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> reps = createNumber("reps", Integer.class);

    public final NumberPath<Double> scheduledDays = createNumber("scheduledDays", Double.class);

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final NumberPath<Double> stability = createNumber("stability", Double.class);

    public final EnumPath<com.kmu.anki.backend.domain.user.entity.CardState> state = createEnum("state", com.kmu.anki.backend.domain.user.entity.CardState.class);

    public final com.kmu.anki.backend.domain.user.entity.QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserCard(String variable) {
        this(UserCard.class, forVariable(variable), INITS);
    }

    public QUserCard(Path<? extends UserCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCard(PathMetadata metadata, PathInits inits) {
        this(UserCard.class, metadata, inits);
    }

    public QUserCard(Class<? extends UserCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.koreanCard = inits.isInitialized("koreanCard") ? new com.kmu.anki.backend.domain.card.entity.QKoreanCard(forProperty("koreanCard")) : null;
        this.user = inits.isInitialized("user") ? new com.kmu.anki.backend.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

