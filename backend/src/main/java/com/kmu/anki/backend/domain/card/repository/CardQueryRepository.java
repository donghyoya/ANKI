package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.entity.QForeignCard;
import com.kmu.anki.backend.domain.card.entity.QKoreanCard;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.entity.QUserCard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class CardQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final JdbcTemplate jdbcTemplate;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private final QUserCard userCard = QUserCard.userCard;

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
                                koreanCard.level,
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

    public CardDetailDto findDetailById(Long cardId, LanguageCode code){
        return queryFactory
                .select(
                        Projections.constructor(
                                CardDetailDto.class,
                                koreanCard.id,
                                koreanCard.koreanWord,
                                foreignCard.foreignWord,
                                koreanCard.level,
                                foreignCard.languageCode,
                                koreanCard.originalLanguage,
                                koreanCard.homographNumber,
                                koreanCard.partsOfSpeech,
                                koreanCard.pronunciation,
                                koreanCard.relatedWords,
                                koreanCard.inflection,
                                koreanCard.exampleUsage
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
