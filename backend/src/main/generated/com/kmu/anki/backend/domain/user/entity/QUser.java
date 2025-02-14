package com.kmu.anki.backend.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -2132683800L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.kmu.anki.backend.domain.usercard.entity.UserCard, com.kmu.anki.backend.domain.usercard.entity.QUserCard> cards = this.<com.kmu.anki.backend.domain.usercard.entity.UserCard, com.kmu.anki.backend.domain.usercard.entity.QUserCard>createList("cards", com.kmu.anki.backend.domain.usercard.entity.UserCard.class, com.kmu.anki.backend.domain.usercard.entity.QUserCard.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.kmu.anki.backend.domain.card.enums.LanguageCode> languageCode = createEnum("languageCode", com.kmu.anki.backend.domain.card.enums.LanguageCode.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath loginPwd = createString("loginPwd");

    public final StringPath name = createString("name");

    public final EnumPath<com.kmu.anki.backend.domain.auth.vo.Role> role = createEnum("role", com.kmu.anki.backend.domain.auth.vo.Role.class);

    public final ListPath<com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory, com.kmu.anki.backend.domain.study.history.entity.QUserStudyHistory> studyHistories = this.<com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory, com.kmu.anki.backend.domain.study.history.entity.QUserStudyHistory>createList("studyHistories", com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory.class, com.kmu.anki.backend.domain.study.history.entity.QUserStudyHistory.class, PathInits.DIRECT2);

    public final NumberPath<Integer> todayReviewWords = createNumber("todayReviewWords", Integer.class);

    public final NumberPath<Integer> todayStudyWords = createNumber("todayStudyWords", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

