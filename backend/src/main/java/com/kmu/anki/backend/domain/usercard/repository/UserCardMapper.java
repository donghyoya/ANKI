package com.kmu.anki.backend.domain.usercard.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCardMapper {
    void studyDeck(Long userId);
}
