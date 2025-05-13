package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.entity.*;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.CardStudyDto;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.QUserCard;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// TODO

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class UserCardQueryRepository {
    private final JPAQueryFactory queryFactory;

    private final QUserCard userCard = QUserCard.userCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;
    private final QCardTopic cardTopic = QCardTopic.cardTopic;

    public Page<UserCardDto> findStudyCards(
            Long userId,
            LanguageCode code,
            CardLevel difficulty,
            CardTopicEnums topic,
            LocalDateTime now,
            StudyType studyType,
            Pageable pageable
    ){
        List<UserCard> userCards = queryFactory
                .select(userCard)
                .from(userCard)
                .join(userCard.koreanCard, koreanCard).fetchJoin()
                .join(koreanCard.cardTopics, cardTopic).fetchJoin()
                .where(
                        combineQuery(userId, difficulty, topic, now, studyType)
                )
                .orderBy(
                        userCard.statePriority.desc(),
                        Expressions.numberTemplate(Double.class, "RANDOM()").asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<UserCard> countq = queryFactory
                .select(userCard)
                .from(userCard)
                .join(userCard.koreanCard, koreanCard)
                .join(koreanCard.cardTopics, cardTopic)
                .where(
                        combineQuery(userId, difficulty, topic, now, studyType)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
        ;
        List<UserCardDto> ret = userCards.stream().map(UserCardDto::of).toList();
        return PageableExecutionUtils.getPage(
                ret, pageable, ()->  countq.fetch().size()
        );
    }

    public Optional<CardStudyDto> findCardStudyDto(
            Long userCardId
    ){
        CardStudyDto cardStudyDto = queryFactory.select(
                        Projections.constructor(
                                CardStudyDto.class,
                                koreanCard.id,
                                userCard.id,
                                userCard.due,
                                userCard.lapses,
                                userCard.lastReview,
                                userCard.reps,
                                userCard.scheduledDays,
                                userCard.stability,
                                userCard.state
                        )
                )
                .from(
                        userCard
                ).join(
                        userCard.koreanCard, koreanCard
                )
                .where(
                        userCardIdEq(userCardId)
                )
                .fetchOne();
        return Optional.ofNullable(cardStudyDto);
    }

    /* 조건식 */
    private Predicate combineQuery(
            Long userId,
            CardLevel difficulty,
            CardTopicEnums topic,
            LocalDateTime now,
            StudyType studyType
    ){
        BooleanBuilder builder = new BooleanBuilder();
        builder
                .and(userIdEq(userId))
                .and(difficultyEq(difficulty))
                .and(topicEq(topic))
                .and(dueBefore(now))
                .and(studyTpye(studyType))
        ;
        return builder;
    }

    public BooleanExpression userCardIdEq(Long userCardId) {
        return userCardId == null ? null : userCard.id.eq(userCardId);
    }

    public BooleanExpression userIdEq(Long userId){
        return userId == null ? null : userCard.userId.eq(userId);
    }

    public BooleanExpression koreanCardIdEq(Long cardId){
        return cardId == null ? null : koreanCard.id.eq(cardId);
    }

    public BooleanExpression languageCodeEq(LanguageCode code){
        return code == null ? null : foreignCard.languageCode.eq(code);
    }

    public BooleanExpression difficultyEq(CardLevel difficulty){
        return difficulty == null ? null : koreanCard.level.eq(difficulty);
    }

    public BooleanExpression topicEq(CardTopicEnums topic){
        return topic == null ? null : cardTopic.topicId.eq(topic);
    }

    public BooleanExpression dueBefore(LocalDateTime dateTime){
        return dateTime == null ? null : userCard.due.before(dateTime);
    }

    public BooleanExpression studyTpye(StudyType type){
        return type == StudyType.study
                ? userCard.state.eq(CardState.New).or(userCard.state.eq(CardState.Learning))
                : userCard.state.eq(CardState.Review).or(userCard.state.eq(CardState.Relearning));
    }
}
