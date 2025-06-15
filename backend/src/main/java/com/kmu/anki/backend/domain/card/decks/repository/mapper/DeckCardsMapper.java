package com.kmu.anki.backend.domain.card.decks.repository.mapper;

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
            @Param("code") String code,
            @Param("level") String level,
            long offset,
            int limit
    );

    int countDeckCardsByLevel(
            @Param("code") String code,
            @Param("level") String level
    );

}
