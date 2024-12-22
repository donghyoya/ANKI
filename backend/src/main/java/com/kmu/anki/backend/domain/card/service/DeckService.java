package com.kmu.anki.backend.domain.card.service;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.dto.CardDto;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeckService {
    private final CardRepository cardRepository;

    public Page<DeckDto> findAll(LanguageCode languageCode, QueryType queryType){
        Pageable pageable = PageRequest.of(0,20);
        if(queryType == QueryType.difficulty){
            return cardRepository.findAllDeckByDifficulty(languageCode, pageable);
        }else {
            return cardRepository.findAllDeckByCategory(languageCode, pageable);
        }
    }

    public Page<CardDto> findDeckCards(LanguageCode languageCode, CardDifficulty cardDifficulty){
        return cardRepository.findDeckCard(languageCode, cardDifficulty, PageRequest.of(0,20)).map(CardDto::of);
    }

    public Page<CardDto> findDeckCards(LanguageCode languageCode, CardMeaningGroup category){
        return cardRepository.findDeckCard(languageCode, category, PageRequest.of(0,20)).map(CardDto::of);
    }

}
