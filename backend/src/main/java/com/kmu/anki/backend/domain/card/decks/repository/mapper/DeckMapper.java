package com.kmu.anki.backend.domain.card.decks.repository.mapper;

import com.kmu.anki.backend.domain.card.decks.dto.DeckDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeckMapper {
    public List<DeckDto> findDeckByDifficulty(@Param("userId") Long userId);
    public List<DeckDto> findDeckByMeaning(@Param("userId") Long userId);
    public List<DeckDto> findDeckByMeaningWithoutUser();

}
