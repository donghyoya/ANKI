package com.kmu.anki.backend.domain.usercard.repository.cache;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserCardCacheRepository {
    Optional<UserCardCacheO> findDailyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums cardTopicEnums);

    Optional<UserCardCacheO> findDailyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel);

    void saveDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums, StudyType studyType, UserCardCacheO cacheO);
    void saveDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel, StudyType studyType, UserCardCacheO cacheO);

    void deleteDailyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums cardTopicEnums);

    void deleteDailyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel);
}
