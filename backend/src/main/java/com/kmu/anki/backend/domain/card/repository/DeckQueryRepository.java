package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.dto.DeckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class DeckQueryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String deckRateByDifficulty = """
            select 
                    korean_cards.level as category,
                    count(korean_card_id) as cards_counts,
                    sum(
                        case when user_cards.due <= now() then 1 else 0 end
                    ) as overdue_counts,
                    sum(
                        case when user_cards.user_card_state = 'review' then 1 else 0 end
                    ) as maturity_counts
            from korean_cards
            join user_cards using (korean_card_id)
            where user_cards.user_id = ?
            group by korean_cards.level;
        """;

    private final String deckRateByMeaning = """
            select 
                    card_topics.topic_id as category,
                    count(korean_card_id) as cards_counts,
                    sum(
                        case when user_cards.due <= now() then 1 else 0 end
                    ) as overdue_counts,
                    sum(
                        case when user_cards.user_card_state = 'review' then 1 else 0 end
                    ) as maturity_counts
            from korean_cards
            join user_cards using (korean_card_id)
            join card_topics using (korean_card_id)
            where user_cards.user_id = ?
            group by card_topics.topic_id;
        """;


    private final RowMapper<DeckDto> deckDtoRowMapper = new RowMapper<DeckDto>() {
        @Override
        public DeckDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            String category = rs.getString("category");
            long cardsCounts = rs.getLong("cards_counts");
            int overdueCounts = rs.getInt("overdue_counts");
            int maturityCounts = rs.getInt("maturity_counts");
            return new DeckDto(category, cardsCounts, overdueCounts, maturityCounts);
        }
    };

    public List<DeckDto> findDeckByDifficulty(Long userId){
        return jdbcTemplate.query(deckRateByDifficulty, deckDtoRowMapper, userId);
    }

    public List<DeckDto> findDeckByMeanging(Long userId){
        return jdbcTemplate.query(deckRateByMeaning, deckDtoRowMapper, userId);
    }

}
