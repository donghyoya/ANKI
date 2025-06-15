package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.card.korean.dto.KoreanCardDto;

import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import lombok.Getter;

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
