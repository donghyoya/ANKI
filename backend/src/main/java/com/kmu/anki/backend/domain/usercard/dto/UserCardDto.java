package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;

import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCardDto {
    private Long userCardId;
    private KoreanCardDto koreanCard;
    private StudyInfoDto studyInfo;

    public UserCardDto(Long userCardId, KoreanCardDto koreanCard, StudyInfoDto studyInfo) {
        this.userCardId = userCardId;
        this.koreanCard = koreanCard;
        this.studyInfo = studyInfo;
    }

    public static UserCardDto of(UserCard userCard){
        return new UserCardDto(
            userCard.getId(),
            KoreanCardDto.of(userCard.getKoreanCard()),
            StudyInfoDto.of(userCard)
        );
    }
}
