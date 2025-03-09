package com.kmu.anki.backend.domain.usercard.repository.cache.impl;

import com.github.benmanes.caffeine.cache.Expiry;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import org.checkerframework.checker.index.qual.NonNegative;

import java.time.*;

public class UtcExpiryImpl implements Expiry<String, UserCardCacheO> {

    @Override
    public long expireAfterCreate(String string, UserCardCacheO cacheO, long currentTime) {
        return getNextMidnightWithOffset(cacheO.getUtcOffset());
    }

    @Override
    public long expireAfterUpdate(String string, UserCardCacheO cacheO, long currentTime, @NonNegative long currentDuration) {
        return currentDuration;
    }

    @Override
    public long expireAfterRead(String string, UserCardCacheO cacheO, long currentTime, @NonNegative long currentDuration) {
        return currentDuration;
    }

    private long getNextMidnightWithOffset(int utcOffset) {
        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofHours(utcOffset));
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay(zoneId);
        return Duration.between(now, nextMidnight).toNanos();
    }


}
