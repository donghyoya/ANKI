package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.TestcontainersConfiguration;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.CardRepository;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:/data.sql", executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS, config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:/drop.sql", executionPhase= Sql.ExecutionPhase.AFTER_TEST_CLASS,  config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
class UserCardStudyRepositoryTest {
    @Autowired private UserCardStudyRepository userCardStudyRepository;
    @Autowired private UserCardRepository userCardRepository;
    @Autowired private CardRepository cardRepository;

    @Test
    void studyDeck() {
        LanguageCode code = LanguageCode.en;
        CardDifficulty difficulty = CardDifficulty.easy;
        Long userDeckId = userCardStudyRepository.studyDeck(1L, code, difficulty);
    }
}