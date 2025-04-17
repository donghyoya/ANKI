package com.kmu.anki.backend.domain.usercard.repository.cache.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import com.kmu.anki.backend.domain.usercard.repository.cache.UserCardCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CaffeineUserCardCacheRepository implements UserCardCacheRepository {
    private final Cache<String, UserCardCacheO> cache;

    public CaffeineUserCardCacheRepository() {
        this.cache = Caffeine.newBuilder()
                .maximumSize(1_0000)
                .expireAfter(new UtcExpiryImpl())
                .build();
    }

    private String buildKey(Long userId, LanguageCode languageCode, Object deckName){
        StringBuilder sb = new StringBuilder();
        sb.append("daily:")
                .append(userId).append(":")
                .append(languageCode).append(":")
                .append(deckName.toString()).append(":")
        ;
        return sb.toString();
    }

    @Override
    public UserCardCacheO findDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums) {
        return cache.getIfPresent(buildKey(userId, languageCode, cardTopicEnums));
    }

    @Override
    public UserCardCacheO findDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel) {
        return cache.getIfPresent(buildKey(userId, languageCode, cardLevel));
    }

    @Override
    public void saveDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums, UserCardCacheO cacheO) {
        cache.put(buildKey(userId, languageCode, cardTopicEnums), cacheO);
    }

    @Override
    public void saveDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel, UserCardCacheO cacheO) {
        cache.put(buildKey(userId, languageCode, cardLevel), cacheO);
    }

    @Override
    public void deleteDailyUserCard(Long userId, LanguageCode languageCode, CardTopicEnums cardTopicEnums) {
        cache.invalidate(buildKey(userId,languageCode,cardTopicEnums));

    }

    @Override
    public void deleteDailyUserCard(Long userId, LanguageCode languageCode, CardLevel cardLevel) {
        cache.invalidate(buildKey(userId,languageCode,cardLevel));
    }
}
