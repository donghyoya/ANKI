package com.kmu.anki.backend.domain.card.korean.repository;

import com.kmu.anki.backend.domain.card.korean.entity.KoreanCard;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class KoreanCardSearchRepository {
    private final EntityManager entityManager;

    public Page<Long> searchKoreanCardIdByKoreanWord(String koreanWord, Pageable pageable){
        SearchSession session = Search.session(entityManager);

        SearchResult<Long> koreanCardIds = session.search(KoreanCard.class)
                .select(f -> f.field("koreanCardId", Long.class))
                .where(f -> f.match().field("koreanWord").matching(koreanWord))
                .fetch((int) pageable.getOffset(), pageable.getPageSize());
        ;
        return PageableExecutionUtils.getPage(koreanCardIds.hits(), pageable, ()->koreanCardIds.total().hitCount());
    }

}
