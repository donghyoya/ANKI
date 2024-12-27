package com.kmu.anki.backend.domain.card.service;

import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.CardQueryRepository;
import com.kmu.anki.backend.domain.card.repository.CardRepository;
import com.kmu.anki.backend.domain.card.repository.KoreanCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CardService {
    private final KoreanCardRepository koreanCardRepository;
    private final CardQueryRepository cardQueryRepository;

    /**
     * cardId로 Card Dto 쿼리하는 간단한 로직
     * @param cardId
     * @return
     */
    public CardDto readCard(Long cardId, LanguageCode code){
        return cardQueryRepository.findById(cardId, code);
    }
}
