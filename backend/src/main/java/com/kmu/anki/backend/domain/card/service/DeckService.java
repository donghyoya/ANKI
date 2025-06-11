package com.kmu.anki.backend.domain.card.service;

import com.kmu.anki.backend.domain.card.dto.*;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.CardQueryRepository;
import com.kmu.anki.backend.domain.card.repository.DeckQueryRepository;
import com.kmu.anki.backend.domain.card.repository.ForeignCardRepository;
import com.kmu.anki.backend.domain.card.repository.KoreanCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeckService {
    private final KoreanCardRepository koreanCardRepository;
    private final ForeignCardRepository foreignCardRepository;
    private final DeckQueryRepository deckQueryRepository;
    private final CardQueryRepository cardQueryRepository;

    public List<DeckDto> readDeckByDifficulty(Long userId){
        List<DeckDto> decks = deckQueryRepository.findDeckByDifficulty(userId);
        return decks;
    }

    public List<DeckDto> readDeckByMeaningGroup(Long userId){
        return deckQueryRepository.findDeckByMeaning(userId);
    }



    public Page<KoreanCardWithForeignWord> findDeckCards(LanguageCode languageCode, CardLevel cardLevel, int page, int pageSize){
        return cardQueryRepository.findDecksCardByLevel(languageCode, cardLevel, PageRequest.of(page, pageSize));
    }

    public Page<KoreanCardWithForeignWord> findDeckCards(LanguageCode languageCode, CardTopicEnums category, int page, int pageSize){
        return cardQueryRepository.findDecksCardByTopic(languageCode, category, PageRequest.of(page, pageSize));
    }

}
