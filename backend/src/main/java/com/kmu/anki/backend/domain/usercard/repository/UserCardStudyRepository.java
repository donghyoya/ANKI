package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Repository
public class UserCardStudyRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static String INSERT_USER_CARDS_BY_MEANING = """
                INSERT INTO user_cards(card_id, user_deck_id, user_card_state)
                SELECT cards.card_id, :userDeckId, 'New'
                FROM cards
                WHERE cards.meaningGroup = :meaningGroup;
            """;

    private static String INSERT_USER_CARDS_BY_DIFFICULTY = """
                INSERT INTO user_cards(card_id, user_deck_id, user_card_state)
                SELECT cards.card_id, :userDeckId, 'New'
                FROM cards
                WHERE cards.difficulty = :difficulty;
            """;
    private static String INSERT_USER_CARDS = """
                INSERT INTO user_cards(card_id, user_deck_id, user_card_state)
                SELECT cards.card_id, :userDeckId, 'New'
                FROM cards
                WHERE cards.difficulty = :difficulty;
            """;


    public void studyDeck(Long userId, CardDifficulty difficulty){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("difficulty", difficulty.toString());
        jdbcTemplate.update(INSERT_USER_CARDS_BY_DIFFICULTY, params);
    }

    public void studyDeck(Long userId, CardMeaningGroup meaningGroup){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("meaningGroup", meaningGroup.toString());
        jdbcTemplate.update(INSERT_USER_CARDS_BY_MEANING, params);
    }

    public void studyDeck(Long userId){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        jdbcTemplate.update(INSERT_USER_CARDS, params);
    }

}
