package com.kmu.anki.backend.domain.user.controller;

import com.kmu.anki.backend.domain.user.entity.User;
import com.kmu.anki.backend.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitUserController {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final InitUserService initUserService;
    @PostConstruct
    public void init(){
        if(ddlMode.equals("create")) {
            initUserService.init();
        }
    }

    @Component
    static class InitUserService{

        @Autowired
        UserRepository userRepository;

        @Transactional
        public void init(){

            User user = new User("test", "1234");

            userRepository.save(user);
        }
    }
}
