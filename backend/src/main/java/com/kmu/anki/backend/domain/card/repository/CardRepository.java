package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.entity.Card;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("select new com.kmu.anki.backend.domain.card.dto.DeckDto(c.languageCode, c.difficulty, count(c)) from Card c where c.languageCode= :languageCode group by c.languageCode, c.difficulty")
    public Page<DeckDto> findAllDeckByDifficulty(@Param("languageCode") LanguageCode languageCode, Pageable pageable);

    @Query("select new com.kmu.anki.backend.domain.card.dto.DeckDto(c.languageCode, c.category, count(c)) from Card c where c.languageCode= :languageCode group by c.languageCode, c.category")
    public Page<DeckDto> findAllDeckByCategory(@Param("languageCode") LanguageCode languageCode, Pageable pageable);

}
