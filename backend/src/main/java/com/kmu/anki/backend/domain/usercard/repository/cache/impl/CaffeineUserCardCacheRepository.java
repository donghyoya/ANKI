package com.kmu.anki.backend.domain.usercard.repository.cache.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import com.kmu.anki.backend.domain.usercard.repository.cache.UserCardCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
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

    private String buildKey(Long userId, StudyType studyType, Object deckName){
        StringBuilder sb = new StringBuilder();
        sb.append("daily:")
                .append(userId).append(":")
                .append(studyType).append(":")
                .append(deckName.toString()).append(":")
        ;
        return sb.toString();
    }

    @Override
    public Optional<UserCardCacheO> findDailyUserCard(Long userId, StudyType studyType, CardTopicEnums cardTopicEnums) {
        return Optional.ofNullable(cache.getIfPresent(buildKey(userId, studyType, cardTopicEnums)));
    }

    @Override
    public Optional<UserCardCacheO> findDailyUserCard(Long userId, StudyType studyType, CardLevel cardLevel) {
        return Optional.ofNullable(cache.getIfPresent(buildKey(userId, studyType, cardLevel)));
    }

    @Override
    public void saveDailyUserCard(Long userId, CardTopicEnums cardTopicEnums, StudyType studyType, UserCardCacheO cacheO) {
        cache.put(buildKey(userId, studyType, cardTopicEnums), cacheO);
    }

    @Override
    public void saveDailyUserCard(Long userId,  CardLevel cardLevel, StudyType studyType, UserCardCacheO cacheO) {
        cache.put(buildKey(userId, studyType,cardLevel), cacheO);
    }

    @Override
    public void deleteDailyUserCard(Long userId, StudyType studyType, CardTopicEnums cardTopicEnums) {
        cache.invalidate(buildKey(userId,studyType,cardTopicEnums));

    }

    @Override
    public void deleteDailyUserCard(Long userId, StudyType studyType, CardLevel cardLevel) {
        cache.invalidate(buildKey(userId,studyType,cardLevel));
    }
}
