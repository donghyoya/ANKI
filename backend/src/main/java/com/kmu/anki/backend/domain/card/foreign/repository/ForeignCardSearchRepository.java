package com.kmu.anki.backend.domain.card.foreign.repository;

import com.kmu.anki.backend.domain.card.foreign.entity.ForeignCard;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
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
public class ForeignCardSearchRepository {
    private final EntityManager entityManager;

    public Page<Long> searchForeignCard(String query, boolean foreignWord, boolean foreignMeaning, Pageable pageable){
        SearchSession session = Search.session(entityManager);

        SearchResult<Long> indexes = session.search(ForeignCard.class)
                .select(f -> f.field("foreignCardId", Long.class))
                .where(f -> {
                    BooleanPredicateClausesStep<?> booleanPredicate = f.bool();
                    if (foreignMeaning) {
                        booleanPredicate.should(f.match().field("foreignMeaning").matching(query));
                    }
                    if (foreignWord) {
                        booleanPredicate.should(f.match().field("foreignWord").matching(query));
                    }
                    return booleanPredicate;
                })
                .fetch((int) pageable.getOffset(), pageable.getPageSize());
        return PageableExecutionUtils.getPage(indexes.hits(), pageable, ()->indexes.total().hitCount());
    }

}
