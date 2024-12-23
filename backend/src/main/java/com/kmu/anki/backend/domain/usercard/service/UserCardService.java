package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.domain.usercard.repository.UserCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserCardService {
    private final UserCardRepository userCardRepository;

    public UserCardDto findByUserCardId(Long userCardId){
        return userCardRepository.findCardByUserCardId(userCardId);
    }

    @Transactional
    public UserCardDto updateUserCard(Long userCardId, LocalDateTime nextStudyDate, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, CardState state){
        UserCard userCard = userCardRepository.findUserCardById(userCardId);
        userCard.update(
                nextStudyDate,
                lapses,
                lastReview,
                reps,
                scheduledDays,
                stability,
                state
        );
        return new UserCardDto(userCard, userCard.getCard());
    }
}
