package com.kmu.anki.backend.domain.user.service;

import com.kmu.anki.backend.domain.user.dto.CreateUserDto;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(CreateUserDto dto){
        User user = new User(dto.loginId, dto.loginPwd);
        return userRepository.save(user);
    }
}
