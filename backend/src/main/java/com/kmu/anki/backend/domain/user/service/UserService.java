package com.kmu.anki.backend.domain.user.service;

import com.kmu.anki.backend.domain.user.dto.CreateUserDto;
import com.kmu.anki.backend.domain.user.dto.UserDto;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCardService userCardService;

    @Transactional
    public User saveUser(CreateUserDto dto){
        User user = new User(dto);
        user = userRepository.save(user);
        userCardService.createUserCards(user.getId());
        return user;
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findByName(username);
    }
}
