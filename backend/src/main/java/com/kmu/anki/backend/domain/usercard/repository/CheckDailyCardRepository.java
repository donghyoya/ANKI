package com.kmu.anki.backend.domain.usercard.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 오늘 학습을 수행했는지에 대한 UserCardRepository
 */
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckDailyCardRepository {
    private final JdbcTemplate jdbcTemplate;

    private static String CHECK_LEARNING_QUERY = """
        SELECT count(user_card_id)
        FROM user_cards uc
        WHERE uc.user_card_id in ?
            AND (
                user_card_state != 'New' 
            );
    """;

    private static String CHECK_REVIEW_QUERY = """
        SELECT count(user_card_id)
        FROM user_cards uc
        WHERE uc.user_card_id in ?
            AND (
                user_card_state != 'Review' 
            );
    """;

    public boolean checkLearning(List<Long> userCardIds){
        Integer count = jdbcTemplate.queryForObject(CHECK_LEARNING_QUERY, Integer.class, userCardIds);
        return count>0;
    }

    public boolean checkReview(List<Long> userCardIds){
        Integer count = jdbcTemplate.queryForObject(CHECK_REVIEW_QUERY, Integer.class, userCardIds);
        return count>0;
    }
}
