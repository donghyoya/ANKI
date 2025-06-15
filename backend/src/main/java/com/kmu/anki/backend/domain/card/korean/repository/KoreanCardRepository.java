package com.kmu.anki.backend.domain.card.korean.repository;

import com.kmu.anki.backend.domain.card.decks.dto.DeckDto;
import com.kmu.anki.backend.domain.card.korean.entity.KoreanCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KoreanCardRepository extends JpaRepository<KoreanCard, Long> {
    @Query("""
        select new com.kmu.anki.backend.domain.card.decks.dto.DeckDto(c.level, count(c))
        from KoreanCard c
        group by c.level
    """)
    public List<DeckDto> findAllDeckByLevel();
}
