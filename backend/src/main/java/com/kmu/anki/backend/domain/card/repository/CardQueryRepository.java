package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.dto.CardMeaningWithForeign;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.entity.*;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
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

// TODO

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class CardQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final JdbcTemplate jdbcTemplate;
    private final ForeignCardTextSearchMapper foreignCardTextSearchMapper;
    private final KoreanCardTextSearchMapper koreanCardTextSearchMapper;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private final QUserCard userCard = QUserCard.userCard;
    private final QCardTopic cardTopic = QCardTopic.cardTopic;
    private final QTopic topic = QTopic.topic1;
    private final QKoreanMeaning koreanMeaning = QKoreanMeaning.koreanMeaning;

    /**
     * koreanCard-ForeignCard를 합쳐서 가져오기
     * @param cardId
     * @return
     */
    public Optional<CardDto> findById(Long cardId, LanguageCode code){
//        CardDto cardDto = queryFactory
//                .select(
//                        Projections.constructor(
//                                CardDto.class,
//                                koreanCard.id,
//                                koreanCard.koreanWord,
//                                foreignCard.foreignWord,
//                                koreanCard.level,
//                                foreignCard.languageCode
//                        )
//                ).from(
//                        foreignCard
//                ).join(foreignCard.koreanCard, koreanCard)
//                .where(
//                        koreanCard.id.eq(cardId)
//                                .and(
//                                        foreignCard.languageCode.eq(code)
//                                )
//                )
//                .fetchOne();
//        return Optional.ofNullable(cardDto);
        return Optional.empty();
    }

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

    /**
     * 의미코드에 맞는 Card들 검색
     * @param languageCode
     * @param level
     * @param pageable
     * @return
     */
    public Page<CardDetailDto> findDecksCardByLevel(
            LanguageCode languageCode,
            CardLevel level,
            Pageable pageable
    ){
//        List<CardDetailDto> cards = queryFactory.select(
//                        Projections.constructor(
//                                CardDetailDto.class,
//                                koreanCard.id,
//                                koreanCard.koreanWord,
//                                foreignCard.foreignWord,
//                                koreanCard.level,
//                                foreignCard.languageCode,
//                                koreanCard.originalLanguage,
//                                koreanCard.homographNumber,
//                                koreanCard.partsOfSpeech,
//                                koreanCard.pronunciation,
//                                koreanCard.relatedWords,
//                                koreanCard.inflection,
//                                koreanCard.exampleUsage
//                        )
//                ).from(koreanCard)
//                .join(koreanCard.foreignCards, foreignCard)
//                .where(
//                        koreanCard.level.eq(level).and(
//                                foreignCard.languageCode.eq(languageCode)
//                        )
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        JPAQuery<KoreanCard> countq = queryFactory.select(koreanCard)
//                .from(koreanCard)
//                .where(
//                        koreanCard.level.eq(level)
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());
//
//        return PageableExecutionUtils.getPage(
//                cards, pageable, ()->  countq.fetch().size()
//        );
        return null;
    }


    /**
     * 의미코드에 맞는 Card들 검색
     * @param languageCode
     * @param category
     * @param pageable
     * @return
     */
    public Page<CardDetailDto> findDecksCardByTopic(
            LanguageCode languageCode,
            CardTopicEnums category,
            Pageable pageable
    ){
//        List<CardDetailDto> cards = queryFactory.select(
//                        Projections.constructor(
//                                CardDetailDto.class,
//                                koreanCard.id,
//                                koreanCard.koreanWord,
//                                foreignCard.foreignWord,
//                                koreanCard.level,
//                                foreignCard.languageCode,
//                                koreanCard.originalLanguage,
//                                koreanCard.homographNumber,
//                                koreanCard.partsOfSpeech,
//                                koreanCard.pronunciation,
//                                koreanCard.relatedWords,
//                                koreanCard.inflection,
//                                koreanCard.exampleUsage
//                        )
//                ).from(koreanCard)
//                .join(koreanCard.foreignCards, foreignCard)
//                .join(koreanCard.cardTopics, cardTopic)
//                .where(
//                    cardTopic.topicId.eq(category).and(
//                        foreignCard.languageCode.eq(languageCode)
//                    )
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        JPAQuery<KoreanCard> countq = queryFactory.select(koreanCard)
//                .from(koreanCard)
//                .join(koreanCard.cardTopics, cardTopic)
//                .where(
//                        cardTopic.topicId.eq(category)
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());
//        return PageableExecutionUtils.getPage(
//            cards, pageable, ()->  countq.fetch().size()
//        );
        return null;
    }

    /**
     * 외국어 문자 검색
     * @param languageCode
     * @param queryText 검색어
     * @return
     */
    public Page<CardDetailDto> searchForeignWord(LanguageCode languageCode, String queryText, Pageable pageable){
        List<CardDetailDto> cardDetailDtos = foreignCardTextSearchMapper.searchForeignWord(languageCode.toString(), queryText, pageable.getOffset(), pageable.getPageSize());
        return PageableExecutionUtils.getPage(
                cardDetailDtos, pageable, () -> foreignCardTextSearchMapper.searchForeignWordCount(languageCode.toString(), queryText)
        );
    }

    public Page<CardDetailDto> searchKoreanWord(LanguageCode languageCode, String queryText, Pageable pageable){
        List<CardDetailDto> cardDetailDtos = koreanCardTextSearchMapper.searchKoreanWord(languageCode.toString(), queryText, pageable.getOffset(), pageable.getPageSize());
        return PageableExecutionUtils.getPage(
                cardDetailDtos, pageable, () -> koreanCardTextSearchMapper.searchKoreanWordCount(languageCode.toString(), queryText)
        );

    }

}
