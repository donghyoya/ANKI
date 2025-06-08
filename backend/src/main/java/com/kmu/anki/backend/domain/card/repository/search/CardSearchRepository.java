package com.kmu.anki.backend.domain.card.repository.search;

import com.kmu.anki.backend.domain.card.dto.ForeignCardSearchResult;
import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CardSearchRepository {
    private final KoreanCardSearchRepository koreanCardSearchRepository;
    private final ForeignCardSearchRepository foreignCardSearchRepository;
    private final JPAQueryFactory queryFactory;

    private QKoreanCard koreanCard = QKoreanCard.koreanCard;
    private QKoreanMeaning koreanMeaning = QKoreanMeaning.koreanMeaning;
    private QCardTopic cardTopic = QCardTopic.cardTopic;
    private QForeignCard foreignCard = QForeignCard.foreignCard;

    public Page<KoreanCardDto> searchKoreanCardByKoreanWord(String koreanWord, Pageable pageable){
        Page<Long> koreanCardIds = koreanCardSearchRepository.searchKoreanCardIdByKoreanWord(koreanWord, pageable);

        List<KoreanCard> koreanCards = queryFactory
                .selectFrom(koreanCard)
                .join(koreanCard.cardTopics, cardTopic).fetchJoin()
                .where(koreanCard.id.in(koreanCardIds.getContent()))
                .fetch();// 검색엔진에서 이미 페이징 처리했음

        return PageableExecutionUtils.getPage(koreanCards.stream().map(KoreanCardDto::of).toList(), pageable, koreanCardIds::getTotalElements);
    }

    public Page<ForeignCardSearchResult> searchForeignCard(String query, boolean foreignWord, boolean foreignMeaning, Pageable pageable){
        Page<Long> ids = foreignCardSearchRepository.searchForeignCard(query, foreignWord, foreignMeaning, pageable);

        List<ForeignCard> foreignCards = queryFactory.selectFrom(foreignCard)
                .join(foreignCard.koreanMeaning, koreanMeaning).fetchJoin()
                .join(koreanMeaning.koreanCard, koreanCard).fetchJoin()
                .join(koreanCard.cardTopics, cardTopic).fetchJoin()
                .fetch();
        return PageableExecutionUtils.getPage(foreignCards.stream().map(ForeignCardSearchResult::of).toList(), pageable, ids::getTotalElements);

    }
}
