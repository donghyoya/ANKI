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
import com.kmu.anki.backend.domain.usercard.exception.DailyStudyNotFinishedException;
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
    private final CheckDailyCardService checkDailyCardService;

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
        Optional<UserCardCacheO> opt = userCardCacheRepository.findDailyUserCard(userId, studyType, cardTopicEnums);
        List<Long> userCardIds = null;
        if(opt.isPresent()){
            userCardIds = opt.get().getUserCardIds();
            // 캐싱 데이터의 개수 맞추는 로직
            if(userCardIds.size() > words){
                userCardIds = userCardIds.subList(0, words);
            }else if(userCardIds.size() < words){
                userCardIds.addAll(userCardQueryRepository.findStudyCardIds(userId, languageCode, null, cardTopicEnums, now, studyType, userCardIds, words-userCardIds.size()+1));
                userCardCacheRepository.saveDailyUserCard(userId, cardTopicEnums, studyType, new UserCardCacheO(userCardIds, user.getUtcOffset()));
            }
        }else {
            userCardIds = userCardQueryRepository.findStudyCardIds(userId, languageCode, null, cardTopicEnums, now, studyType, null, words);
            userCardCacheRepository.saveDailyUserCard(userId, cardTopicEnums, studyType, new UserCardCacheO(userCardIds, user.getUtcOffset()));
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
        Optional<UserCardCacheO> opt = userCardCacheRepository.findDailyUserCard(userId, studyType, cardLevel);
        List<Long> userCardIds = null;
        if(opt.isPresent()){
            userCardIds = opt.get().getUserCardIds();
            if(userCardIds.size() > words){
                userCardIds = userCardIds.subList(0, words);
            }else if(userCardIds.size() < words){
                userCardIds.addAll(userCardQueryRepository.findStudyCardIds(userId, languageCode, cardLevel, null, now, studyType, userCardIds, words-userCardIds.size()+1));
                userCardCacheRepository.saveDailyUserCard(userId, cardLevel, studyType, new UserCardCacheO(userCardIds, user.getUtcOffset()));
            }
        }else {
            userCardIds = userCardQueryRepository.findStudyCardIds(userId, languageCode, cardLevel, null, now, studyType, null, words);
            userCardCacheRepository.saveDailyUserCard(userId, cardLevel, studyType, new UserCardCacheO(userCardIds, user.getUtcOffset()));
        }
        return userCardQueryRepository.findStudyCardByIds(userCardIds);
    }

    // 학습계속하기

    public List<UserCardDto> continueStudyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums cardTopicEnums){
        LocalDateTime now = LocalDateTime.now();
        // 유저 정보를 꺼내라
        User user = userRepository.findById(userId).orElseThrow();
        // 오늘 공부 끝났는지 확인
        boolean isComplete = checkDailyCardService.checkLearingComplete(userId, languageCode, StudyType.study, cardTopicEnums);
        if(!isComplete){
            throw new DailyStudyNotFinishedException();
        }
        // 오늘 공부했던 단어 삭제
        this.deleteCache(userId, studyType, cardTopicEnums);

        // 오늘 공부할 단어 개수 꺼내기
        Integer words = studyType == StudyType.study ? user.getDailyStudyWords() : user.getDailyReviewWords();

        // 새로 공부할 단어 가져와서 캐시에 넣기
        List<Long> userCardIds = userCardQueryRepository.findStudyCardIds(userId, languageCode, null, cardTopicEnums, now, studyType, null, words);
        userCardCacheRepository.saveDailyUserCard(userId, cardTopicEnums, studyType, new UserCardCacheO(userCardIds, user.getUtcOffset()));
        return userCardQueryRepository.findStudyCardByIds(userCardIds);
    }

    public List<UserCardDto> continueStudyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel){
        LocalDateTime now = LocalDateTime.now();
        // 유저 정보를 꺼내라
        User user = userRepository.findById(userId).orElseThrow();
        // 오늘 공부 끝났는지 확인
        boolean isComplete = checkDailyCardService.checkLearingComplete(userId, languageCode, StudyType.study, cardLevel);
        if(!isComplete){
            throw new DailyStudyNotFinishedException();
        }
        // 오늘 공부했던 단어 삭제
        this.deleteCache(userId, studyType, cardLevel);

        // 오늘 공부할 단어 개수 꺼내기
        Integer words = studyType == StudyType.study ? user.getDailyStudyWords() : user.getDailyReviewWords();

        // 새로 공부할 단어 가져와서 캐시에 넣기
        List<Long> userCardIds = userCardQueryRepository.findStudyCardIds(userId, languageCode, cardLevel, null, now, studyType, null, words);
        userCardCacheRepository.saveDailyUserCard(userId, cardLevel, studyType, new UserCardCacheO(userCardIds, user.getUtcOffset()));
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
    public void deleteCache(Long userId, StudyType studyType, CardTopicEnums cardTopicEnums) {
        userCardCacheRepository.deleteDailyUserCard(userId, studyType, cardTopicEnums);
    }

    public void deleteCache(Long userId, StudyType studyType, CardLevel cardLevel) {
        userCardCacheRepository.deleteDailyUserCard(userId, studyType, cardLevel);
    }
}
