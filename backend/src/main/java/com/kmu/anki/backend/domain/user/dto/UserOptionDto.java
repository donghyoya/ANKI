package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserOptionDto {
    private Long id;
    private Integer dailyStudyWords;
    private Integer dailyReviewWords;
    private Integer utcOffset;
    private LanguageCode languageCode;

    public UserOptionDto(Long id, Integer dailyStudyWords, Integer dailyReviewWords, LanguageCode languageCode, Integer utcOffset) {
        this.id = id;
        this.dailyStudyWords = dailyStudyWords;
        this.dailyReviewWords = dailyReviewWords;
        this.languageCode = languageCode;
        this.utcOffset = utcOffset;
    }

    public static UserOptionDto of(User user) {
        return new UserOptionDto(
            user.getId(),
            user.getDailyStudyWords(),
            user.getDailyReviewWords(),
            user.getLanguageCode(),
            user.getUtcOffset()
        );
    }
}
