package com.kmu.anki.backend.domain.card.decks.repository;

import com.kmu.anki.backend.domain.card.controller.option.QueryType;
import com.kmu.anki.backend.domain.card.decks.repository.mapper.DeckMapper;
import com.kmu.anki.backend.domain.card.decks.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.korean.repository.KoreanCardRepository;
import com.kmu.anki.backend.domain.usercard.dto.DeckCheckDto;
import com.kmu.anki.backend.domain.usercard.repository.CheckDailyCardRepository;
import com.kmu.anki.backend.domain.usercard.repository.cache.UserCardCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class DeckQueryRepository {
    private final DeckMapper deckMapper;
    private final KoreanCardRepository koreanCardRepository;
    private final CheckDailyCardRepository cardCacheRepository;

    public List<DeckDto> findDeckByDifficulty(Long userId){
        if(userId == null){
            return koreanCardRepository.findAllDeckByLevel();
        }else {
            List<DeckDto> decks = deckMapper.findDeckByDifficulty(userId);
            Map<Object, DeckCheckDto> map = cardCacheRepository.checkDeckCards(userId, QueryType.LEVEL);
            return checkDailyCardComplete(decks, map, QueryType.LEVEL);
        }
    }

    public List<DeckDto> findDeckByMeaning(Long userId){
        if(userId == null){
            return deckMapper.findDeckByMeaningWithoutUser();
        }else {
            List<DeckDto> decks = deckMapper.findDeckByMeaning(userId);
            Map<Object, DeckCheckDto> map = cardCacheRepository.checkDeckCards(userId, QueryType.TOPIC);
            return checkDailyCardComplete(decks, map, QueryType.TOPIC);
        }
    }

    private List<DeckDto> checkDailyCardComplete(List<DeckDto> decks, Map<Object, DeckCheckDto> map, QueryType type){
        for (DeckDto deck : decks){
            Object key;
            if(type == QueryType.LEVEL){
                key = CardLevel.valueOf(deck.getCategory());
            }else {
                key = CardTopicEnums.valueOf(deck.getCategory());
            }
            DeckCheckDto deckCheckDto = map.get(key);
            deck.check(deckCheckDto);
        }
        return decks;
    }
}
