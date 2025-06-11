package com.kmu.anki.backend.domain.card.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeckCardsMapper {
    List<Long> findDeckCardsByTopic(
            @Param("code") String code,
            @Param("topic") String topic,
            long offset,
            int limit
    );

    int countDeckCardsByTopic(
            @Param("code") String code,
            @Param("topic") String topic
    );

    List<Long> findDeckCardsByLevel(
            @Param("level") String level,
            @Param("code") String code,
            long offset,
            int limit
    );

    int countDeckCardsByLevel(
            @Param("level") String level,
            @Param("code") String code
    );

}
