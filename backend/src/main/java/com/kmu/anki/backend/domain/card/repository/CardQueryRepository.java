package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.*;
import com.kmu.anki.backend.domain.card.entity.*;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.foreign.entity.QForeignCard;
import com.kmu.anki.backend.domain.card.korean.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.korean.entity.QKoreanCard;
import com.kmu.anki.backend.domain.card.korean.entity.QKoreanMeaning;
import com.kmu.anki.backend.domain.usercard.entity.QUserCard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class CardQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final JdbcTemplate jdbcTemplate;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private final QUserCard userCard = QUserCard.userCard;
    private final QCardTopic cardTopic = QCardTopic.cardTopic;
    private final QTopic topic = QTopic.topic1;
    private final QKoreanMeaning koreanMeaning = QKoreanMeaning.koreanMeaning;

    /**
     * 카드의 세부 정보 반환 반환
     * @param cardId koreanCard ID
     * @param code 언어코드
     * @return 카드 세부정보 DTO
     */
    public Optional<CardDetailDto> findDetailById(Long cardId, LanguageCode code){
        KoreanCard card = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.cardTopics, cardTopic).fetchJoin()
                .where(koreanCard.id.eq(cardId))
                .fetchOne();
        List<CardMeaningWithForeign> cardMeanings = queryFactory.select(Projections.constructor(
                        CardMeaningWithForeign.class,
                        koreanMeaning.originalLanguage,
                        koreanMeaning.partsOfSpeech,
                        koreanMeaning.pronunciation,
                        koreanMeaning.relatedWords,
                        koreanMeaning.inflection,
                        koreanMeaning.exampleUsage,
                        foreignCard.languageCode,
                        foreignCard.foreignWord,
                        foreignCard.foreignMeaning
                ))
                .from(koreanMeaning)
                .join(koreanMeaning.foreignCards, foreignCard)
                .where(foreignCard.languageCode.eq(code).and(koreanMeaning.koreanCardId.eq(cardId)))
                .fetch();
        CardDetailDto cardDetailDto = CardDetailDto.of(card, cardMeanings);
        return Optional.ofNullable(cardDetailDto);
    }
}
