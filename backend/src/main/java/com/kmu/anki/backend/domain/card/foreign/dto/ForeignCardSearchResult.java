package com.kmu.anki.backend.domain.card.foreign.dto;

import com.kmu.anki.backend.domain.card.dto.CardMeaningWithForeign;
import com.kmu.anki.backend.domain.card.korean.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.foreign.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.korean.entity.KoreanMeaning;
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
