package com.kmu.anki.backend.domain.card.service;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.CardQueryRepository;
import com.kmu.anki.backend.domain.card.korean.repository.KoreanCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CardService {
    private final CardQueryRepository cardQueryRepository;

    /**
     * Card의 상세 정보
     * @param cardId
     * @param code
     * @return
     */
    public CardDetailDto readCardDetail(Long cardId, LanguageCode code){
        return cardQueryRepository.findDetailById(cardId, code).orElseThrow();
    }
}
