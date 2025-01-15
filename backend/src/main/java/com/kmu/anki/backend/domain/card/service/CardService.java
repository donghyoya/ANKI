package com.kmu.anki.backend.domain.card.service;

import com.kmu.anki.backend.domain.card.dto.CardDetailDto;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.CardQueryRepository;
import com.kmu.anki.backend.domain.card.repository.KoreanCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * Card의 상세 정보
     * @param cardId
     * @param code
     * @return
     */
    public CardDetailDto readCardDetail(Long cardId, LanguageCode code){
        return cardQueryRepository.findDetailById(cardId, code);
    }

    public Page<CardDetailDto> searchForeignCards(LanguageCode code, String queryText, int page, int pageSize){
        return cardQueryRepository.searchForeignWord(code, queryText, PageRequest.of(page, pageSize));
    }

}
