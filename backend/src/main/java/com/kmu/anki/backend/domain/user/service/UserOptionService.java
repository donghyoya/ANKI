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

    /**
     * JWT로 생성된 Authentication 객체로부터 userOption 정보를 추출하는 메서드
     * @param username authentication에서 바로 뽑은 username
     * @return Useroption
     */
    @Transactional(readOnly = true)
    public UserOptionDto readOption(String username){
        Long userId;
        try {
            userId = Long.parseLong(username);
        }catch (RuntimeException ex){
            throw ex;
        }
        User user = userRepository.findById(userId).orElseThrow();
        return UserOptionDto.of(user);
    }


    @Transactional
    public UserOptionDto updateOption(Long id, Integer todayStudyWords, Integer todayReviewWords, LanguageCode languageCode, Integer utcOffset){
        User user = userRepository.findById(id).orElseThrow();
        user.update(todayStudyWords, todayReviewWords, languageCode, utcOffset);
        return UserOptionDto.of(user);
    }


}
