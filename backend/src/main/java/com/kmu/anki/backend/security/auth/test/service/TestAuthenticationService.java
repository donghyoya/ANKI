package com.kmu.anki.backend.security.auth.test.service;

import com.kmu.anki.backend.domain.auth.legacy.vo.Role;
import com.kmu.anki.backend.domain.user.dto.CreateUserDto;
import com.kmu.anki.backend.domain.user.dto.UserDto;
import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.service.UserService;
import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TestAuthenticationService {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    private final String TEST_USERNAME = "testuser1";

    @Transactional
    public String getTestToken(){
        UserDto testUser = null;
        Optional<User> user = userService.findUserByUsername(TEST_USERNAME);
        if(user.isPresent()){
            testUser = UserDto.of(user.get());
        }else {
            CreateUserDto createUser = CreateUserDto.builder()
                    .name(TEST_USERNAME)
                    .role(Role.USER)
                    .build();
            User newTestUser = userService.saveUser(createUser);
            testUser = UserDto.of(newTestUser);
        }
        return jwtTokenService.generateToken(testUser.getName(), Map.of("Role", "ROLE_USER"));
    }
}
