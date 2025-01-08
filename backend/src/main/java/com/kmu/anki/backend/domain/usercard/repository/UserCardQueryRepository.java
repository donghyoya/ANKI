package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.entity.QCardTopic;
import com.kmu.anki.backend.domain.card.entity.QForeignCard;
import com.kmu.anki.backend.domain.card.entity.QKoreanCard;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.dto.CardStudyDto;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.QUserCard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
            CardTopicEnums meaningGroup, // TODO
            LocalDateTime now,
            Pageable pageable
    ){
        List<UserCardDto> contents = queryFactory.select(
                        Projections.constructor(
                                UserCardDto.class,
                                koreanCard.id,
                                koreanCard.koreanWord,
                                foreignCard.foreignWord,
                                koreanCard.level,
                                foreignCard.languageCode,
                                userCard.id,
                                userCard.due,
                                userCard.lapses,
                                userCard.lastReview,
                                userCard.reps,
                                userCard.scheduledDays,
                                userCard.stability,
                                userCard.state,
                                userCard.difficulty
                        )
                )
                .from(
                        koreanCard
                ).join(
                        koreanCard.userCards, userCard
                ).join(
                        koreanCard.foreignCards, foreignCard
                ).join(
                        koreanCard.cardTopics, cardTopic
                )
                .where(
                        combineQuery(
                                userId,
                                code,
                                difficulty,
                                meaningGroup,
                                now
                        )
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<UserCardDto> countQuery = queryFactory
                .select(
                        Projections.constructor(
                                UserCardDto.class,
                                koreanCard.id,
                                koreanCard.koreanWord,
                                foreignCard.foreignWord,
                                koreanCard.level,
                                foreignCard.languageCode,
                                userCard.id,
                                userCard.due,
                                userCard.lapses,
                                userCard.lastReview,
                                userCard.reps,
                                userCard.scheduledDays,
                                userCard.stability,
                                userCard.state,
                                userCard.difficulty
                        )
                )
                .from(
                        koreanCard
                ).join(
                        koreanCard.userCards, userCard
                ).join(
                        koreanCard.foreignCards, foreignCard
                )
                .where(
                        combineQuery(
                                userId,
                                code,
                                difficulty,
                                meaningGroup,
                                now
                        )
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(
                contents,
                pageable,
                ()->countQuery.fetch().size()
        );
    }

    public CardStudyDto findCardStudyDto(
            Long cardId
    ){
        return queryFactory.select(
                        Projections.constructor(
                                CardStudyDto.class,
                                koreanCard.id,
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
                        koreanCardIdEq(cardId)
                )
                .fetchOne();
    }

    /* 조건식 */
    private Predicate combineQuery(
            Long userId,
            LanguageCode code,
            CardLevel difficulty,
            CardTopicEnums topic,
            LocalDateTime now
    ){
        BooleanBuilder builder = new BooleanBuilder();
        builder
                .and(userIdEq(userId))
                .and(languageCodeEq(code))
                .and(difficultyEq(difficulty))
                .and(topicEq(topic))
                .and(dueBefore(now))
        ;
        return builder;
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

}
