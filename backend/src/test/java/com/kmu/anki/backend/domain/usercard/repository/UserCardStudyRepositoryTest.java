package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.TestcontainersConfiguration;
import com.kmu.anki.backend.global.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.junit.jupiter.api.Assertions.*;

class UserCardStudyRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private UserCardStudyRepository userCardStudyRepository;
    @Autowired
    private UserCardRepository userCardRepository;

    @Test
    void studyDeck() {
        userCardStudyRepository.studyDeck(2L);
        long count = userCardRepository.countByUserId(2L);
        assertEquals(60L, count);
    }
}