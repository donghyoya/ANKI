package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.entity.*;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.entity.QUserCard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class CardQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final JdbcTemplate jdbcTemplate;
    private final ForeignCardTextSearchMapper foreignCardTextSearchMapper;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private final QUserCard userCard = QUserCard.userCard;
    private final QCardTopic cardTopic = QCardTopic.cardTopic;
    private final QTopic topic = QTopic.topic1;

    /**
     * koreanCard-ForeignCard를 합쳐서 가져오기
     * @param cardId
     * @return
     */
    public Optional<CardDto> findById(Long cardId, LanguageCode code){
        CardDto cardDto = queryFactory
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
        return Optional.ofNullable(cardDto);
    }

    /**
     * 카드의 세부 정보 반환 반환
     * @param cardId koreanCard ID
     * @param code 언어코드
     * @return 카드 세부정보 DTO
     */
    public Optional<CardDetailDto> findDetailById(Long cardId, LanguageCode code){
        CardDetailDto cardDetailDto = queryFactory
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
        return Optional.ofNullable(cardDetailDto);
    }

    /**
     * 의미코드에 맞는 Card들 검색
     * @param languageCode
     * @param category
     * @param pageable
     * @return
     */
    public Page<CardDto> findDecksCardByTopic(
            LanguageCode languageCode,
            CardTopicEnums category,
            Pageable pageable
    ){
        List<CardDto> cards = queryFactory.select(
                        Projections.constructor(
                                CardDto.class,
                                koreanCard.id,
                                koreanCard.koreanWord,
                                foreignCard.foreignWord,
                                koreanCard.level,
                                foreignCard.languageCode
                        )
                ).from(koreanCard)
                .join(koreanCard.foreignCards, foreignCard)
                .join(koreanCard.cardTopics, cardTopic)
                .where(
                    cardTopic.topicId.eq(category).and(
                        foreignCard.languageCode.eq(languageCode)
                    )
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<KoreanCard> countq = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.cardTopics, cardTopic)
                .where(
                        cardTopic.topicId.eq(category)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return PageableExecutionUtils.getPage(
            cards, pageable, ()->  countq.fetch().size()
        );
    }

    /**
     * 외국어 문자 검색
     * @param languageCode
     * @param queryText 검색어
     * @return
     */
    public Page<CardDetailDto> searchForeignWord(LanguageCode languageCode, String queryText, Pageable pageable){
        List<CardDetailDto> cardDetailDtos = foreignCardTextSearchMapper.searchForeignWord(languageCode.toString(), queryText, pageable.getOffset(), pageable.getPageSize());
        foreignCardTextSearchMapper.searchForeignWordCount(languageCode.toString(), queryText);

        return PageableExecutionUtils.getPage(
                cardDetailDtos, pageable, () -> foreignCardTextSearchMapper.searchForeignWordCount(languageCode.toString(), queryText)
        );
    }

}
