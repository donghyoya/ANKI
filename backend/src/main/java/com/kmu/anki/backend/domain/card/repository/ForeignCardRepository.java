package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ForeignCardRepository extends JpaRepository<ForeignCard, Long> {
    @Query("""
        select fc
        from ForeignCard fc join fetch fc.koreanCard kc
        where
            fc.languageCode = :languageCode
            and kc.level = :difficulty
    """)
    public Page<ForeignCard> findDeckCard(@Param("languageCode") LanguageCode languageCode, @Param("difficulty") CardLevel difficulty, Pageable pageable);

}
