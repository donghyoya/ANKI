package com.kmu.anki.backend.domain.study.history.service;

import com.kmu.anki.backend.domain.card.controller.option.QueryType;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.study.history.dto.UserStudyHistoryDto;
import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import com.kmu.anki.backend.domain.study.history.repository.UserStudyHistoryRepository;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserStudyHistoryService {
    private final UserStudyHistoryRepository userStudyHistoryRepository;
    private final UserRepository userRepository;

    /* create */

    @Transactional
    public void createHistory(StudyType studyType, QueryType deckType, CardLevel cardLevel, Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        UserStudyHistory studyHistory = UserStudyHistory.from(studyType, deckType, cardLevel, null, user);
        user.addStudyHistory(studyHistory);
    }

    @Transactional
    public void createHistory(StudyType studyType, QueryType deckType, CardTopicEnums cardTopic, Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        UserStudyHistory studyHistory = UserStudyHistory.from(studyType, deckType, null, cardTopic, user);
        user.addStudyHistory(studyHistory);
    }


    public Page<UserStudyHistoryDto> readUserHistory(Long userId, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return userStudyHistoryRepository.findByUserIdOrderByStudyDateDesc(userId, pageRequest).map(UserStudyHistoryDto::of);
    }

    public UserStudyHistoryDto readLatestDecks(Long userId){
        Page<UserStudyHistoryDto> userStudyHistoryDto = userStudyHistoryRepository.findByUserIdOrderByStudyDateDesc(userId, PageRequest.of(0, 1)).map(UserStudyHistoryDto::of);
        if (userStudyHistoryDto.getContent().size() == 0){
            return UserStudyHistoryDto.builder().build();
        }else {
            return userStudyHistoryDto.getContent().get(0);
        }
    }
}
