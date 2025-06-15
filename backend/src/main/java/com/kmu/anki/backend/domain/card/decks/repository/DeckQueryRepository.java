package com.kmu.anki.backend.domain.card.decks.repository;

import com.kmu.anki.backend.domain.card.decks.repository.mapper.DeckMapper;
import com.kmu.anki.backend.domain.card.decks.dto.DeckDto;
import com.kmu.anki.backend.domain.card.korean.repository.KoreanCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class DeckQueryRepository {
    private final DeckMapper deckMapper;
    private final KoreanCardRepository koreanCardRepository;

    public List<DeckDto> findDeckByDifficulty(Long userId){
        if(userId == null){
            return koreanCardRepository.findAllDeckByLevel();
        }else {
            return deckMapper.findDeckByDifficulty(userId);
        }
    }

    public List<DeckDto> findDeckByMeaning(Long userId){
        if(userId == null){
            return deckMapper.findDeckByMeaningWithoutUser();
        }else {
            return deckMapper.findDeckByMeaning(userId);
        }
    }

}
