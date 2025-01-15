package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ForeignCardTextSearchMapper {
    List<CardDetailDto> searchForeignWord(
            @Param("languageCode") String languageCode,
            @Param("queryText") String queryText,
            long offset,
            int limit
    );

    int searchForeignWordCount(@Param("languageCode") String languageCode, @Param("queryText") String queryText);
}
