package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KoreanCardRepository extends JpaRepository<KoreanCard, Long> {
    @Query("""
        select new com.kmu.anki.backend.domain.card.dto.DeckDto(c.difficulty, count(c)) 
        from KoreanCard c 
        group by c.difficulty
    """)
    public List<DeckDto> findAllDeckByDifficulty();

    @Query("""
        select new com.kmu.anki.backend.domain.card.dto.DeckDto(c.meaningGroup, count(c)) 
        from KoreanCard c 
        group by c.meaningGroup
    """)
    public List<DeckDto> findAllDeckByMeaningGroup();
}
