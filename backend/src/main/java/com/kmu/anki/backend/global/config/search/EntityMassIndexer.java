package com.kmu.anki.backend.global.config.search;

import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
//@Component
public class EntityMassIndexer implements CommandLineRunner {
    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {
        SearchSession searchSession = Search.session(entityManager);

        MassIndexer massIndexer = searchSession
                .massIndexer(KoreanCard.class)
                .threadsToLoadObjects(1)
                .batchSizeToLoadObjects(512);

        log.info("[mass-indexer] KoreanCard mass index Start");
        massIndexer.startAndWait();
        log.info("[mass-indexer] KoreanCard mass index End");

        massIndexer = searchSession
                .massIndexer(ForeignCard.class)
                .threadsToLoadObjects(1)
                .batchSizeToLoadObjects(512);

        log.info("[mass-indexer] ForeignCard mass index Start");
        massIndexer.startAndWait();
        log.info("[mass-indexer] ForeignCard mass index End");

    }
}
