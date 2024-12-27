package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCardQueryRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private UserCardQueryRepository userCardQueryRepository;

    @Autowired
    private UserCardRepository userCardRepository;

    @Test
    void findCardByUserCardId() {
        UserCardDto userCardDto = userCardQueryRepository.findCardByUserCardId(1L);
        List<UserCard> all = userCardRepository.findAll();
        assertNotNull(userCardDto);
//        assertNotEquals(0, all.size());
    }
}