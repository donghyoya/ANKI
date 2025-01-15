package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Repository
public class UserCardStudyRepository {
    private final UserCardMapper userCardMapper;

    public void studyDeck(Long userId){
        userCardMapper.studyDeck(userId);
    }

}
