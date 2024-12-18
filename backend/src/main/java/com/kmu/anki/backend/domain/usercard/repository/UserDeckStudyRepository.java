package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Repository
public class UserDeckStudyRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static String INSERT_USER_DECK = """
                INSERT INTO user_decks(user_id, language_code, difficulty)
                values (:userId, :languageCode, :difficulty)
                RETURNING user_deck_id;
            """;

    private static String INSERT_USER_CARDS = """
                INSERT INTO user_cards(card_id, user_deck_id)
                SELECT cards.card_id, :userDeckId
                FROM cards
                WHERE cards.language_code = :languageCode AND cards.difficulty = :difficulty;
            """;


    public Long studyDeck(Long userId, LanguageCode code, CardDifficulty difficulty){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("languageCode", code);
        params.put("difficulty", difficulty);
        Long userDeckId = jdbcTemplate.queryForObject(INSERT_USER_DECK, params, Long.class);
        params.put("userDeckId", userDeckId);
        jdbcTemplate.update(INSERT_USER_CARDS, params);
        return userDeckId;
    }
}
