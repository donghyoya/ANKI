package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.entity.Card;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("select new com.kmu.anki.backend.domain.card.dto.DeckDto(c.languageCode, c.difficulty, count(c)) from Card c where c.languageCode= :languageCode group by c.languageCode, c.difficulty")
    public Page<DeckDto> findAllDeckByDifficulty(@Param("languageCode") LanguageCode languageCode, Pageable pageable);

    /**
     * 테스트용 함수
     * @param languageCode
     * @return
     */
    @Query("select c from Card c where c.languageCode=:languageCode and c.difficulty = :difficulty")
    public List<Card> findCardsForTest(@Param("difficulty") CardDifficulty difficulty, @Param("languageCode") LanguageCode languageCode);

    @Query("select new com.kmu.anki.backend.domain.card.dto.DeckDto(c.languageCode, c.meaningGroup, count(c)) from Card c where c.languageCode= :languageCode group by c.languageCode, c.meaningGroup")
    public Page<DeckDto> findAllDeckByCategory(@Param("languageCode") LanguageCode languageCode, Pageable pageable);

    @Query("select c from Card c where c.languageCode = :languageCode and c.difficulty = :difficulty")
    public Page<Card> findDeckCard(@Param("languageCode") LanguageCode languageCode,@Param("difficulty") CardDifficulty difficulty, Pageable pageable);

    @Query("select c from Card c where c.languageCode = :languageCode and c.difficulty = :category")
    public Page<Card> findDeckCard(@Param("languageCode") LanguageCode languageCode, @Param("category") CardMeaningGroup category, Pageable pageable);

}
