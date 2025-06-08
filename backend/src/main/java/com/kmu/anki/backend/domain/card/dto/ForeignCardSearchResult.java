package com.kmu.anki.backend.domain.card.dto;

import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.entity.KoreanMeaning;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;

@Getter
public class ForeignCardSearchResult {
    private KoreanCardDto koreanCard;
    private CardMeaningWithForeign foreignCard;

    public ForeignCardSearchResult(KoreanCardDto koreanCard, CardMeaningWithForeign foreignCard) {
        this.koreanCard = koreanCard;
        this.foreignCard = foreignCard;
    }

    public static ForeignCardSearchResult of(ForeignCard foreignCard){
        KoreanMeaning koreanMeaning = foreignCard.getKoreanMeaning();
        CardMeaningWithForeign cardMeaningWithForeign = new CardMeaningWithForeign(
                koreanMeaning.getOriginalLanguage(),
                koreanMeaning.getPartsOfSpeech(),
                koreanMeaning.getPronunciation(),
                koreanMeaning.getRelatedWords(),
                koreanMeaning.getInflection(),
                koreanMeaning.getExampleUsage(),
                foreignCard.getLanguageCode(),
                foreignCard.getForeignWord(),
                foreignCard.getForeignMeaning()
        );
        KoreanCardDto koreanCardDto = KoreanCardDto.of(koreanMeaning.getKoreanCard());
        return new ForeignCardSearchResult(koreanCardDto, cardMeaningWithForeign);
    }
}
