package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.*;
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
import java.util.Map;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

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
    public Page<KoreanCardWithForeignWord> findDecksCardByLevel(
            LanguageCode languageCode,
            CardLevel level,
            Pageable pageable
    ){
        List<KoreanCard> cards = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.foreignCards, foreignCard)
                .where(koreanCard.level.eq(level)
                        .and(foreignCard.languageCode.eq(languageCode)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<KoreanCard> countq = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.foreignCards, foreignCard)
                .where(koreanCard.level.eq(level)
                        .and(foreignCard.languageCode.eq(languageCode)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        Map<Long, List<String>> foreignWords = findForeignWordsByKoreanCardAndLanguageCode(cards, languageCode);
        return PageableExecutionUtils.getPage(
                cards.stream().map(card->KoreanCardWithForeignWord.of(card, foreignWords)).toList(), pageable, ()->  countq.fetch().size()
        );
    }


    /**
     * 의미코드에 맞는 Card들 검색
     * @param languageCode
     * @param topic
     * @param pageable
     * @return
     */
    public Page<KoreanCardWithForeignWord> findDecksCardByTopic(
            LanguageCode languageCode,
            CardTopicEnums topic,
            Pageable pageable
    ){
        List<KoreanCard> cards = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.cardTopics, cardTopic)
                .join(koreanCard.foreignCards, foreignCard)
                .where(
                        cardTopic.topicId.eq(topic)
                                .and(foreignCard.languageCode.eq(languageCode))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<KoreanCard> countq = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.cardTopics, cardTopic)
                .join(koreanCard.foreignCards, foreignCard)
                .where(
                        cardTopic.topicId.eq(topic)
                                .and(foreignCard.languageCode.eq(languageCode))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        Map<Long, List<String>> foreignWords = findForeignWordsByKoreanCardAndLanguageCode(cards, languageCode);
        return PageableExecutionUtils.getPage(
                cards.stream().map(card->KoreanCardWithForeignWord.of(card, foreignWords)).toList(), pageable, ()->  countq.fetch().size()
        );
    }
    
    public Map<Long, List<String>> findForeignWordsByKoreanCardAndLanguageCode(List<KoreanCard> cards, LanguageCode code){
        return queryFactory
                .from(foreignCard)
                .where(foreignCard.koreanCard.in(cards).and(foreignCard.languageCode.eq(code)))
                .transform(groupBy(foreignCard.koreanCardId).as(list(foreignCard.foreignWord)));
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
