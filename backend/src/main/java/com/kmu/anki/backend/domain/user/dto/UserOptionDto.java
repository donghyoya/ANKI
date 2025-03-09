package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class UserOptionDto {
    private Long id;
    private Integer todayStudyWords;
    private Integer todayReviewWords;
    private Integer utcOffset;
    private LanguageCode languageCode;

    public UserOptionDto(Long id, Integer todayStudyWords, Integer todayReviewWords, LanguageCode languageCode, Integer utcOffset) {
        this.id = id;
        this.todayStudyWords = todayStudyWords;
        this.todayReviewWords = todayReviewWords;
        this.languageCode = languageCode;
        this.utcOffset = utcOffset;
    }

    public static UserOptionDto of(User user) {
        return new UserOptionDto(
            user.getId(),
            user.getTodayStudyWords(),
            user.getTodayStudyWords(),
            user.getLanguageCode(),
            user.getUtcOffset()
        );
    }
}
