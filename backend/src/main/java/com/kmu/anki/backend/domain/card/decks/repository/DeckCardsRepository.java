package com.kmu.anki.backend.domain.card.decks.repository;

import com.kmu.anki.backend.domain.card.decks.repository.mapper.DeckCardsMapper;
import com.kmu.anki.backend.domain.card.foreign.entity.QForeignCard;
import com.kmu.anki.backend.domain.card.korean.dto.KoreanCardWithForeignWord;
import com.kmu.anki.backend.domain.card.entity.*;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.foreign.repository.ForeignCardQueryRepository;
import com.kmu.anki.backend.domain.card.korean.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.korean.entity.QKoreanCard;
import com.kmu.anki.backend.domain.card.korean.entity.QKoreanMeaning;
import com.kmu.anki.backend.domain.usercard.entity.QUserCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Transactional(readOnly = true)
@Repository
@RequiredArgsConstructor
public class DeckCardsRepository {
    private final ForeignCardQueryRepository foreignCardQueryRepository;
    private final DeckCardsMapper deckCardsMapper;
    private final JPAQueryFactory queryFactory;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;
    private final QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private final QUserCard userCard = QUserCard.userCard;
    private final QCardTopic cardTopic = QCardTopic.cardTopic;
    private final QTopic topic = QTopic.topic1;
    private final QKoreanMeaning koreanMeaning = QKoreanMeaning.koreanMeaning;

    public Page<KoreanCardWithForeignWord> findDecksCardByTopic(
            LanguageCode languageCode,
            CardTopicEnums topic,
            Pageable pageable
    ){
        List<Long> koreanCardIds = deckCardsMapper.findDeckCardsByTopic(languageCode.toString(), topic.toString(), pageable.getOffset(), pageable.getPageSize());
        List<KoreanCard> koreanCards = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.cardTopics, cardTopic).fetchJoin()
                .where(koreanCard.id.in(koreanCardIds)).fetch();
        Map<Long, List<String>> foreignWords = foreignCardQueryRepository.findForeignWordsByKoreanCardAndLanguageCode(koreanCardIds, languageCode);
        return PageableExecutionUtils.getPage(
                koreanCards.stream().map(card->KoreanCardWithForeignWord.of(card, foreignWords)).toList(),
                pageable,
                ()->deckCardsMapper.countDeckCardsByTopic(languageCode.toString(), topic.toString())
        );
    }

    public Page<KoreanCardWithForeignWord> findDecksCardByLevel(
            LanguageCode languageCode,
            CardLevel level,
            Pageable pageable
    ){
        List<Long> koreanCardIds = deckCardsMapper.findDeckCardsByLevel(languageCode.toString(), level.toString(), pageable.getOffset(), pageable.getPageSize());
        List<KoreanCard> koreanCards = queryFactory.select(koreanCard)
                .from(koreanCard)
                .join(koreanCard.cardTopics, cardTopic).fetchJoin()
                .where(koreanCard.id.in(koreanCardIds)).fetch();
        Map<Long, List<String>> foreignWords = foreignCardQueryRepository.findForeignWordsByKoreanCardAndLanguageCode(koreanCardIds, languageCode);
        return PageableExecutionUtils.getPage(
                koreanCards.stream().map(card->KoreanCardWithForeignWord.of(card, foreignWords)).toList(),
                pageable,
                ()->deckCardsMapper.countDeckCardsByLevel(languageCode.toString(), level.toString())
        );
    }


}
