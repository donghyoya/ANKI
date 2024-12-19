package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.domain.usercard.repository.UserCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserCardService {
    private final UserCardRepository userCardRepository;

    public UserCardDto findByUserCardId(Long userCardId){
        return userCardRepository.findCardByUserCardId(userCardId);
    }

    @Transactional
    public UserCardDto updateUserCard(Long userCardId, Integer score, LocalDateTime nextStudyDate){
        UserCard userCard = userCardRepository.findUserCardById(userCardId);
        userCard.update(score, nextStudyDate);
        return new UserCardDto(userCard, userCard.getCard());
    }
}
