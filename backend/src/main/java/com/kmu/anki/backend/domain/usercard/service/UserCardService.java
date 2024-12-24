package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.domain.usercard.repository.UserCardRepository;
import com.kmu.anki.backend.domain.usercard.repository.UserCardStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserCardService {
    private final UserCardRepository userCardRepository;
    private final UserCardStudyRepository userCardStudyRepository;

    /* CREATE */

    @Transactional
    public void createUserCards(Long userId){
        if(!userCardRepository.existsByUserId(userId)){
            /* UserId를 여러개 만들게 하지 않기 위함 */
            userCardStudyRepository.studyDeck(userId);
        }
    }

    /* READ */

    public UserCardDto findByUserCardId(Long userCardId){
        return userCardRepository.findCardByUserCardId(userCardId);
    }

    /* UPDATE */

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

    /* DELETE */

}
