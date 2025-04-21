package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.exception.UserOptionRequiredException;
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

    public static void validate(UserOptionDto userOptionDto){
        if(userOptionDto.getUtcOffset() == null){
            throw new UserOptionRequiredException("utc-offset");
        }
        if(userOptionDto.getLanguageCode() == null){
            throw new UserOptionRequiredException("language-code");
        }
        if(userOptionDto.getDailyReviewWords() == null){
            throw new UserOptionRequiredException("DailyReviewWords");
        }
        if(userOptionDto.getDailyStudyWords() == null){
            throw new UserOptionRequiredException("DailyStudyWords");
        }
    }
}
