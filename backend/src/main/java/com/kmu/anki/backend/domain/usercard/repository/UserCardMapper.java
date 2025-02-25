package com.kmu.anki.backend.domain.usercard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCardMapper {
    void studyDeck(@Param("userId") Long userId);
}
