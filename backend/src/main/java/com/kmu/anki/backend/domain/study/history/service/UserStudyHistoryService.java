package com.kmu.anki.backend.domain.study.history.service;

import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import com.kmu.anki.backend.domain.study.history.repository.UserStudyHistoryRepository;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import lombok.RequiredArgsConstructor;
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
    public void createHistory(StudyType studyType, String deckType , Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        UserStudyHistory studyHistory = UserStudyHistory.from(studyType, deckType.toString(), user);
        user.addStudyHistory(studyHistory);
    }
}
