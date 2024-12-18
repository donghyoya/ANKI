package com.kmu.anki.backend.domain.usercard.dto;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.entity.UserDeck;
import lombok.Getter;

@Getter
public class UserDeckDto {
    private Long id;
    private LanguageCode languageCode;
    private CardDifficulty difficulty;
    private long counts;

    public UserDeckDto(UserDeck deck, long counts){
        this.id = deck.getId();
        this.languageCode = deck.getLanguageCode();
        this.difficulty = deck.getDifficulty();
        this.counts = counts;
    }

    UserDeckDto(Long id, LanguageCode languageCode, CardDifficulty difficulty, long counts) {
        this.id = id;
        this.languageCode = languageCode;
        this.difficulty = difficulty;
        this.counts = counts;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Long id;
        private LanguageCode code;
        private CardDifficulty difficulty;
        private long counts;

        public UserDeckDto build(){
            return new UserDeckDto(id, code, difficulty, counts);
        }

        public Builder userDeck(UserDeck deck){
            return this
                    .id(deck.getId())
                    .code(deck.getLanguageCode())
                    .difficulty(deck.getDifficulty())
            ;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder code(LanguageCode code) {
            this.code = code;
            return this;
        }

        public Builder difficulty(CardDifficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder counts(long counts) {
            this.counts = counts;
            return this;
        }
    }
}
