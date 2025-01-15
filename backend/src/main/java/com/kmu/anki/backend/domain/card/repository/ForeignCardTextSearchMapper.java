package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ForeignCardTextSearchMapper {
    CardDetailDto searchForeignWord(@Param("languageCode") String languageCode, @Param("queryText") String queryText);
}
