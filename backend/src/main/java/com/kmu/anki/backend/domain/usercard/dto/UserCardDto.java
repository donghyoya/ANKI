package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.card.entity.Card;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCardDto {
    private Long cardId;
    private Long userCardId;
    private String koreanWord;
    private String foerienWord;
    private CardDifficulty difficulty;
    private LanguageCode languageCode;
    private Integer score;
    private LocalDateTime nextStudyDate;

    public UserCardDto(UserCard uc, Card c){
        this.cardId = c.getId();
        this.userCardId = uc.getId();
        this.koreanWord = c.getKoreanWord();
        this.foerienWord = c.getForeignWord();
        this.difficulty = c.getDifficulty();
        this.languageCode = c.getLanguageCode();
        this.score = uc.getScore();
        this.nextStudyDate = uc.getNextStudyDate();
    }

    public UserCardDto(Long cardId, Long userCardId, String koreanWord, String foerienWord, CardDifficulty difficulty, LanguageCode languageCode, Integer score, LocalDateTime nextStudyDate) {
        this.cardId = cardId;
        this.userCardId = userCardId;
        this.koreanWord = koreanWord;
        this.foerienWord = foerienWord;
        this.difficulty = difficulty;
        this.languageCode = languageCode;
        this.score = score;
        this.nextStudyDate = nextStudyDate;
    }
}
