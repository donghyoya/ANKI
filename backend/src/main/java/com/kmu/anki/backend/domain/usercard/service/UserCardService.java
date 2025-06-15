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
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<UserCardDto> readStudyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums cardTopicEnums){
        // 오늘 날짜를 꺼낸다
        LocalDateTime now = LocalDateTime.now();
        // 유저 정보를 꺼내라
        User user = userRepository.findById(userId).orElseThrow();
        // 오늘 공부할 단어 개수 꺼내기
        Integer words = studyType == StudyType.study ? user.getDailyStudyWords() : user.getDailyReviewWords();
        // cache에서 꺼내기
        Optional<UserCardCacheO> opt = userCardCacheRepository.findDailyUserCard(userId, languageCode, studyType, cardTopicEnums);
        List<Long> userCardIds = null;
        if(opt.isPresent()){
            userCardIds = opt.get().getUserCardIds();
        }else {
            userCardIds = userCardQueryRepository.findStudyCardIds(userId, languageCode, null, cardTopicEnums, now, studyType, words);
        }
        return userCardQueryRepository.findStudyCardByIds(userCardIds);
    }

    public List<UserCardDto> readStudyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel){
        LocalDateTime now = LocalDateTime.now();
        // 유저 정보를 꺼내라
        User user = userRepository.findById(userId).orElseThrow();
        // 오늘 공부할 단어 목록
        Integer words = studyType == StudyType.study ? user.getDailyStudyWords() : user.getDailyReviewWords();
        // cache에서 꺼내기
        Optional<UserCardCacheO> opt = userCardCacheRepository.findDailyUserCard(userId, languageCode, studyType, cardLevel);
        List<Long> userCardIds = null;
        if(opt.isPresent()){
            userCardIds = opt.get().getUserCardIds();
        }else {
            userCardIds = userCardQueryRepository.findStudyCardIds(userId, languageCode, cardLevel, null, now, studyType, words);
        }
        return userCardQueryRepository.findStudyCardByIds(userCardIds);
    }


    public CardStudyDto readCardStudyInfo(Long userCardId){
        return userCardQueryRepository.findCardStudyDto(userCardId).orElseThrow();
    }

    /* UPDATE */

    @Transactional
    public CardStudyDto updateUserCard(Long userCardId, LocalDateTime due, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, Double difficulty,Integer state){
        UserCard userCard = userCardRepository.findById(userCardId).orElseThrow();
        userCard.update(
                due,
                lapses,
                lastReview,
                reps,
                scheduledDays,
                stability,
                difficulty,
                CardState.of(state)
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
