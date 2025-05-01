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
        Integer words = studyType == StudyType.study ? user.getDailyStudyWords() : user.getDailyReviewWords();
        PageRequest pageRequest = PageRequest.of(0, words);
        UserCardCacheO dailyUserCard = userCardCacheRepository.findDailyUserCard(userId, languageCode, studyType, cardTopicEnums);
        Page<UserCardDto> ret = null;
        if(dailyUserCard == null){
            ret = userCardQueryRepository.findStudyCards(userId, languageCode, null, cardTopicEnums, now, studyType, pageRequest);
            dailyUserCard = new UserCardCacheO(ret, user.getUtcOffset());
            userCardCacheRepository.saveDailyUserCard(userId, languageCode, cardTopicEnums, studyType, dailyUserCard);
        }else { // 캐시된 것이 없는 경우
            int cachedWords = dailyUserCard.getUserCardDtos().getSize();
            if(words < cachedWords){
                List<UserCardDto> contents = dailyUserCard.getUserCardDtos().getContent().subList(0, words);// userOptions에 맞게 데이터 빼기
                ret = PageableExecutionUtils.getPage(
                        contents,
                        PageRequest.of(0, words),
                        ()->words
                );
            }else if(words > cachedWords){
                Page<UserCardDto> cards = userCardQueryRepository.findStudyCards(userId, languageCode, null, cardTopicEnums, now, studyType, pageRequest);
                List<UserCardDto> concat = new ArrayList<>(dailyUserCard.getUserCardDtos().getContent());
                concat.addAll(cards.getContent());
                ret = PageableExecutionUtils.getPage(
                        concat,
                        PageRequest.of(0, words),
                        ()->cards.getSize()
                );
                userCardCacheRepository.deleteDailyUserCard(userId, languageCode, studyType, cardTopicEnums);
                userCardCacheRepository.saveDailyUserCard(userId, languageCode, cardTopicEnums, studyType, new UserCardCacheO(ret, user.getUtcOffset()));
            }else {
                ret = dailyUserCard.getUserCardDtos();
            }
        }
        return ret;
    }

    public Page<UserCardDto> readStudyUserCard(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel){
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(userId).orElseThrow();
        Integer words = studyType == StudyType.study ? user.getDailyStudyWords() : user.getDailyReviewWords();
        PageRequest pageRequest = PageRequest.of(0, words);
        UserCardCacheO dailyUserCard = userCardCacheRepository.findDailyUserCard(userId, languageCode, studyType, cardLevel);
        Page<UserCardDto> ret = null;
        if(dailyUserCard == null){ // 캐시된 것이 있는 경우
            ret = userCardQueryRepository.findStudyCards(userId, languageCode, cardLevel, null, now, studyType,pageRequest);
            dailyUserCard = new UserCardCacheO(ret, user.getUtcOffset());
            userCardCacheRepository.saveDailyUserCard(userId, languageCode, cardLevel, studyType, dailyUserCard);
        }else { // 캐시된 것이 없는 경우
            int cachedWords = dailyUserCard.getUserCardDtos().getSize();
            if(words < cachedWords){
                List<UserCardDto> contents = dailyUserCard.getUserCardDtos().getContent().subList(0, words);// userOptions에 맞게 데이터 빼기
                ret = PageableExecutionUtils.getPage(
                        contents,
                        PageRequest.of(0, words),
                        ()->words
                );
            }else if(words > cachedWords){
                Page<UserCardDto> cards = userCardQueryRepository.findStudyCards(userId, languageCode, cardLevel, null, now, studyType, PageRequest.of(0, words - cachedWords));
                List<UserCardDto> concat = new ArrayList<>(dailyUserCard.getUserCardDtos().getContent());
                concat.addAll(cards.getContent());
                ret = PageableExecutionUtils.getPage(
                        concat,
                        PageRequest.of(0, words),
                        ()->cards.getSize()
                );
                userCardCacheRepository.deleteDailyUserCard(userId, languageCode, studyType, cardLevel);
                userCardCacheRepository.saveDailyUserCard(userId, languageCode, cardLevel, studyType, new UserCardCacheO(ret, user.getUtcOffset()));
            }else {
                ret = dailyUserCard.getUserCardDtos();
            }
        }
        return ret;
    }


    public CardStudyDto readCardStudyInfo(Long cardId){
        return userCardQueryRepository.findCardStudyDto(cardId).orElseThrow();
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
