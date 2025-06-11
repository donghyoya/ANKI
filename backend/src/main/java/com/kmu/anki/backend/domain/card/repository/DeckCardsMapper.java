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

}
