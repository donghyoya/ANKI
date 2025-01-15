package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class DeckQueryRepository {
    private final DeckMapper deckMapper;

    public List<DeckDto> findDeckByDifficulty(Long userId){
        return deckMapper.findDeckByDifficulty(userId);
    }

    public List<DeckDto> findDeckByMeaning(Long userId){
        return deckMapper.findDeckByMeaning(userId);
    }

}
