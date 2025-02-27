package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeckMapper {
    public List<DeckDto> findDeckByDifficulty(@Param("userId") Long userId);
    public List<DeckDto> findDeckByMeaning(@Param("userId") Long userId);
    public List<DeckDto> findDeckByMeaningWithoutUser();

}
