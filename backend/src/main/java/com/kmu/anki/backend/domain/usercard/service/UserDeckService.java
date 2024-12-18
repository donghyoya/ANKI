package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.dto.UserDeckDto;
import com.kmu.anki.backend.domain.usercard.entity.UserDeck;
import com.kmu.anki.backend.domain.usercard.repository.UserCardRepository;
import com.kmu.anki.backend.domain.usercard.repository.UserDeckRepository;
import com.kmu.anki.backend.domain.usercard.repository.UserDeckStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserDeckService {
    private final UserDeckRepository userDeckRepository;
    private final UserCardRepository userCardRepository;
    private final UserDeckStudyRepository deckStudyRepository;

    @Transactional
    public UserDeckDto study(LanguageCode languageCode, CardDifficulty difficulty, Long userId){
        Long id = deckStudyRepository.studyDeck(userId, languageCode, difficulty);
        return userDeckRepository.findUserDeckDtoById(id);
    }

    public Page<UserDeckDto> findUserDeckByUserId(Long userId){
        return userDeckRepository.findUserDeckDtoByUserId(userId, PageRequest.of(0,20));
    }
}
