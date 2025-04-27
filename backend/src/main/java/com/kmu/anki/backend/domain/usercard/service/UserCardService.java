package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.CardStudyDto;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.domain.usercard.repository.UserCardQueryRepository;
import com.kmu.anki.backend.domain.usercard.repository.UserCardRepository;
import com.kmu.anki.backend.domain.usercard.repository.UserCardStudyRepository;
import com.kmu.anki.backend.domain.usercard.repository.cache.UserCardCacheRepository;
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
    private final UserCardCacheRepository userCardCacheRepository;

    /* CREATE */

    @Transactional
    public void createUserCards(Long userId){
        if(!userCardRepository.existsByUserId(userId)){
            /* UserId를 여러개 만들게 하지 않기 위함 */
            userCardStudyRepository.studyDeck(userId);
        }
    }

    /* READ */

    public Page<UserCardDto> readStudyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums cardTopicEnums){
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElseThrow();
        PageRequest pageRequest = PageRequest.of(0, user.getDailyStudyWords());
        UserCardCacheO dailyUserCard = userCardCacheRepository.findDailyUserCard(userId, languageCode, studyType, cardTopicEnums);
        if(dailyUserCard == null){
            Page<UserCardDto> studyCards = userCardQueryRepository.findStudyCards(userId, languageCode, null, cardTopicEnums, now, studyType, pageRequest);
            dailyUserCard = new UserCardCacheO(studyCards, user.getUtcOffset());
            userCardCacheRepository.saveDailyUserCard(userId, languageCode, cardTopicEnums, studyType, dailyUserCard);
        }
        return dailyUserCard.getUserCardDtos();
    }

    public Page<UserCardDto> readStudyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel){
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElseThrow();
        PageRequest pageRequest = PageRequest.of(0, user.getDailyStudyWords());
        UserCardCacheO dailyUserCard = userCardCacheRepository.findDailyUserCard(userId, languageCode, studyType, cardLevel);
        if(dailyUserCard == null){
            Page<UserCardDto> studyCards = userCardQueryRepository.findStudyCards(userId, languageCode, cardLevel, null, now, studyType,pageRequest);
            dailyUserCard = new UserCardCacheO(studyCards, user.getUtcOffset());
            userCardCacheRepository.saveDailyUserCard(userId, languageCode, cardLevel, studyType, dailyUserCard);
        }
        return dailyUserCard.getUserCardDtos();
    }


    public CardStudyDto readCardStudyInfo(Long cardId){
        return userCardQueryRepository.findCardStudyDto(cardId).orElseThrow();
    }

    /* UPDATE */

    @Transactional
    public CardStudyDto updateUserCard(Long userCardId, LocalDateTime due, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, CardState state){
        UserCard userCard = userCardRepository.findById(userCardId).orElseThrow();
        userCard.update(
                due,
                lapses,
                lastReview,
                reps,
                scheduledDays,
                stability,
                state
        );
        return CardStudyDto.of(userCard);
    }

    /* DELETE */
    public void deleteCache(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums cardTopicEnums) {
        userCardCacheRepository.deleteDailyUserCard(userId, languageCode, studyType, cardTopicEnums);
    }

    public void deleteCache(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel) {
        userCardCacheRepository.deleteDailyUserCard(userId, languageCode, studyType, cardLevel);
    }
}
