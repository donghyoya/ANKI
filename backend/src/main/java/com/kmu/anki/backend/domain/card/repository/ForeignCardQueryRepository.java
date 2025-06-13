package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.entity.QForeignCard;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class ForeignCardQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QForeignCard foreignCard = QForeignCard.foreignCard;

    public Map<Long, List<String>> findForeignWordsByKoreanCardAndLanguageCode(List<Long> cards, LanguageCode code){
        return queryFactory
                .from(foreignCard)
                .where(foreignCard.koreanCardId.in(cards).and(foreignCard.languageCode.eq(code)))
                .transform(groupBy(foreignCard.koreanCardId).as(list(foreignCard.foreignWord)));
    }

}
