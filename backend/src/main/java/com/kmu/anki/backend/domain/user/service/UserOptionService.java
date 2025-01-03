package com.kmu.anki.backend.domain.user.service;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserOptionService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserOptionDto readOption(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return UserOptionDto.of(user);
    }

    @Transactional
    public UserOptionDto updateOption(Long id, Integer todayStudyWords, Integer todayReviewWords, LanguageCode languageCode){
        User user = userRepository.findById(id).orElseThrow();
        user.update(todayStudyWords, todayReviewWords, languageCode);
        return UserOptionDto.of(user);
    }

}
