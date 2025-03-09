package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserOptionDto {
    private Integer dailyStudyWords;
    private Integer dailyReviewWords;
    private Integer utcOffset;
    private LanguageCode languageCode;

    public UserOptionDto(Integer dailyStudyWords, Integer dailyReviewWords, LanguageCode languageCode, Integer utcOffset) {
        this.dailyStudyWords = dailyStudyWords;
        this.dailyReviewWords = dailyReviewWords;
        this.languageCode = languageCode;
        this.utcOffset = utcOffset;
    }

    public static UserOptionDto of(User user) {
        return new UserOptionDto(
            user.getDailyStudyWords(),
            user.getDailyReviewWords(),
            user.getLanguageCode(),
            user.getUtcOffset()
        );
    }
}
