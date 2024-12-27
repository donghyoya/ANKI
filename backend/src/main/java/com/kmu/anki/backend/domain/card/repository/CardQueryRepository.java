package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.entity.QForeignCard;
import com.kmu.anki.backend.domain.card.entity.QKoreanCard;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class CardQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;

    /**
     * koreanCard-ForeignCard를 합쳐서 가져오기
     * @param cardId
     * @return
     */
    public CardDto findById(Long cardId, LanguageCode code){
        return queryFactory
                .select(
                        Projections.constructor(
                                CardDto.class,
                                koreanCard.id,
                                koreanCard.koreanWord,
                                foreignCard.foreignWord,
                                koreanCard.difficulty,
                                foreignCard.languageCode
                        )
                ).from(
                        foreignCard
                ).join(foreignCard.koreanCard, koreanCard)
                .where(
                        koreanCard.id.eq(cardId)
                                .and(
                                        foreignCard.languageCode.eq(code)
                                )
                )
                .fetchOne();
    }
}
