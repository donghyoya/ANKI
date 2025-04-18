package com.kmu.anki.backend.domain.usercard.repository.cache;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import org.springframework.data.domain.Page;

public interface UserCardCacheRepository {
    UserCardCacheO findDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums);

    UserCardCacheO findDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel);

    void saveDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums, UserCardCacheO cacheO);
    void saveDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel, UserCardCacheO cacheO);

    void deleteDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums);

    void deleteDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel);
}
