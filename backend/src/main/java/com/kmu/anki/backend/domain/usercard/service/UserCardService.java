package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.domain.usercard.repository.UserCardQueryRepository;
import com.kmu.anki.backend.domain.usercard.repository.UserCardRepository;
import com.kmu.anki.backend.domain.usercard.repository.UserCardStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserCardService {
    private final UserRepository userRepository;
    private final UserCardRepository userCardRepository;
    private final UserCardStudyRepository userCardStudyRepository;
    private final UserCardQueryRepository userCardQueryRepository;

    /* CREATE */

    @Transactional
    public void createUserCards(Long userId){
        if(!userCardRepository.existsByUserId(userId)){
            /* UserId를 여러개 만들게 하지 않기 위함 */
            userCardStudyRepository.studyDeck(userId);
        }
    }

    /* READ */

    public Page<UserCardDto> readStudyUserCard(Long userId, LanguageCode languageCode, CardMeaningGroup cardMeaningGroup){
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElseThrow();
        PageRequest pageRequest = PageRequest.of(0, user.getTodayStudyWords());
        return userCardRepository.findStudyCard(userId, languageCode, cardMeaningGroup, now, pageRequest);
    }

    public Page<UserCardDto> readStudyUserCard(Long userId, LanguageCode languageCode, CardDifficulty cardDifficulty){
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElseThrow();
        PageRequest pageRequest = PageRequest.of(0, user.getTodayStudyWords());
        return userCardRepository.findStudyCard(userId, languageCode, cardDifficulty, now, pageRequest);
    }


    public UserCardDto findByUserCardId(Long userCardId){
        return userCardQueryRepository.findCardByUserCardId(userCardId);
    }

    /* UPDATE */

    @Transactional
    public UserCardDto updateUserCard(Long userCardId, LocalDateTime nextStudyDate, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, CardState state){
        UserCard userCard = userCardRepository.findById(userCardId).orElseThrow();
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
