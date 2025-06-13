package com.kmu.anki.backend.domain.card.service;

import com.kmu.anki.backend.domain.card.controller.option.ForeignCardSearchOption;
import com.kmu.anki.backend.domain.card.dto.ForeignCardSearchResult;
import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.dto.KoreanCardWithForeignWord;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.search.CardSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CardSearchService {
    private final CardSearchRepository cardSearchRepository;

    public Page<KoreanCardWithForeignWord> searchKoreanCardByKoreanWord(String koreanWord, LanguageCode code, int pageNumber, int pageSize){
        return cardSearchRepository.searchKoreanCardByKoreanWord(koreanWord, code, PageRequest.of(pageNumber, pageSize));
    }

    public Page<ForeignCardSearchResult> searchForeignCard(String query, ForeignCardSearchOption option, int pageNumber, int pageSize){
        boolean word = false;
        boolean meaning = false;
        if(option == ForeignCardSearchOption.BOTH){
            word = true;
            meaning = true;
        } else if(option == ForeignCardSearchOption.FOREIGN_MEANING){
            meaning = true;
        }else if(option == ForeignCardSearchOption.FOREIGN_WORD){
            word = true;
        }

        return cardSearchRepository.searchForeignCard(query, word, meaning, PageRequest.of(pageNumber, pageSize));
    }
}
