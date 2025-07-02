package com.kmu.anki.backend.domain.usercard.service;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import com.kmu.anki.backend.domain.usercard.repository.CheckDailyCardRepository;
import com.kmu.anki.backend.domain.usercard.repository.cache.UserCardCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CheckDailyCardService {
    private final CheckDailyCardRepository checkDailyCardRepository;
    private final UserCardCacheRepository cardCacheRepository;

    /**
     * 오늘 학습이 끝났으면 true
     */
    public boolean checkLearingComplete(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel){
        Optional<UserCardCacheO> opt = cardCacheRepository.findDailyUserCard(userId, studyType, cardLevel);
        if(opt.isEmpty()){
            return false;
        }
        List<Long> userCardIds = opt.get().getUserCardIds();
        return checkDailyCardRepository.checkLearning(userCardIds);
    }

    public boolean checkLearingComplete(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums topic){
        Optional<UserCardCacheO> opt = cardCacheRepository.findDailyUserCard(userId, studyType, topic);
        if(opt.isEmpty()){
            return false;
        }
        List<Long> userCardIds = opt.get().getUserCardIds();
        return checkDailyCardRepository.checkLearning(userCardIds);
    }


    /**
     * 오늘 복습이 끝났으면 true
     */
    public boolean checkReviewComplete(Long userId, LanguageCode languageCode, StudyType studyType, CardLevel cardLevel){
        Optional<UserCardCacheO> opt = cardCacheRepository.findDailyUserCard(userId, studyType, cardLevel);
        if(opt.isEmpty()){
            return false;
        }
        List<Long> userCardIds = opt.get().getUserCardIds();
        return checkDailyCardRepository.checkReview(userCardIds);
    }

    public boolean checkReviewComplete(Long userId, LanguageCode languageCode, StudyType studyType, CardTopicEnums topic){
        Optional<UserCardCacheO> opt = cardCacheRepository.findDailyUserCard(userId, studyType, topic);
        if(opt.isEmpty()){
            return false;
        }
        List<Long> userCardIds = opt.get().getUserCardIds();
        return checkDailyCardRepository.checkReview(userCardIds);
    }
}
