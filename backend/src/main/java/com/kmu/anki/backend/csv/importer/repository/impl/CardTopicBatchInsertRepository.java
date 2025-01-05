package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.csv.importer.repository.CsvBatchInsertRepository;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardTopicBatchInsertRepository implements CsvBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    private String INSERT_TOPICS = """
                INSERT INTO topics (topic_id, topic)
                VALUES (?, ?);
            """;

    private String INSERT_CARD_TOPICS = """
                INSERT INTO card_topics (card_id, topic_id)
                VALUES (?, ?);
            """;

    @Transactional
    @Override
    public void batchInsert(List<String[]> rows) {
        batchInsertTopic();
    }

    private void batchInsertTopic(){
        CardTopicEnums[] topics = CardTopicEnums.values();
        jdbcTemplate.batchUpdate(INSERT_TOPICS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, i+1);
                ps.setString(2, topics[i].toString());
            }

            @Override
            public int getBatchSize() {
                return topics.length;
            }
        });

        for(int i=0;i<topics.length;i++){

        }
    }

    @Override
    public boolean isSupport(String type) {
        return true;
    }

}
