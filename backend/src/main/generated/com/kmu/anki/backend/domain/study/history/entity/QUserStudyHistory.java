package com.kmu.anki.backend.domain.study.history.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserStudyHistory is a Querydsl query type for UserStudyHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserStudyHistory extends EntityPathBase<UserStudyHistory> {

    private static final long serialVersionUID = -1357873683L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserStudyHistory userStudyHistory = new QUserStudyHistory("userStudyHistory");

    public final EnumPath<com.kmu.anki.backend.domain.card.enums.CardLevel> cardLevel = createEnum("cardLevel", com.kmu.anki.backend.domain.card.enums.CardLevel.class);

    public final EnumPath<com.kmu.anki.backend.domain.card.enums.CardTopicEnums> cardTopic = createEnum("cardTopic", com.kmu.anki.backend.domain.card.enums.CardTopicEnums.class);

    public final EnumPath<com.kmu.anki.backend.domain.card.controller.QueryType> deckType = createEnum("deckType", com.kmu.anki.backend.domain.card.controller.QueryType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> studyDate = createDateTime("studyDate", java.time.LocalDateTime.class);

    public final EnumPath<com.kmu.anki.backend.domain.usercard.controller.form.StudyType> studyType = createEnum("studyType", com.kmu.anki.backend.domain.usercard.controller.form.StudyType.class);

    public final com.kmu.anki.backend.domain.user.entity.QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserStudyHistory(String variable) {
        this(UserStudyHistory.class, forVariable(variable), INITS);
    }

    public QUserStudyHistory(Path<? extends UserStudyHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserStudyHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserStudyHistory(PathMetadata metadata, PathInits inits) {
        this(UserStudyHistory.class, metadata, inits);
    }

    public QUserStudyHistory(Class<? extends UserStudyHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kmu.anki.backend.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

