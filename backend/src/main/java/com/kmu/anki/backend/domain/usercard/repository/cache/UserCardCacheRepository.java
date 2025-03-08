package com.kmu.anki.backend.domain.usercard.repository.cache;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import org.springframework.data.domain.Page;

public interface UserCardCacheRepository {
    Page<UserCardDto> findByDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums);

    Page<UserCardDto> findDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel);

    void saveDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums, Page<UserCardDto> cards);
    void saveDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel, Page<UserCardDto> cards);

}
