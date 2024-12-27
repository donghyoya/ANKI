package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.entity.QForeignCard;
import com.kmu.anki.backend.domain.card.entity.QKoreanCard;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.QUserCard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class UserCardQueryRepository {
    private final JPAQueryFactory queryFactory;

    private final QUserCard userCard = QUserCard.userCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;

    public UserCardDto findCardByUserCardId(
            Long userCardId
    ){
        UserCardDto userCardDto = queryFactory.select(
                        Projections.constructor(
                                UserCardDto.class,
                                koreanCard.id,
                                koreanCard.koreanWord,
                                foreignCard.foreignWord,
                                koreanCard.difficulty,
                                foreignCard.languageCode,
                                userCard.id,
                                userCard.score,
                                userCard.nextStudyDate,
                                userCard.lapses,
                                userCard.lastReview,
                                userCard.reps,
                                userCard.scheduledDays,
                                userCard.stability,
                                userCard.state
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
                        userCard.id.eq(userCardId)
                                .and(
                                        foreignCard.languageCode.eq(LanguageCode.en)
                                )
                )
                .fetchOne();
        return userCardDto;
    }
}
