package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.csv.importer.repository.CsvBatchInsertRepository;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
@RequiredArgsConstructor
public class CardTopicBatchInsertRepository implements CsvBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    private String INSERT_TOPICS = """
                INSERT INTO topics (topic_id)
                VALUES (?);
            """;

    private String INSERT_CARD_TOPICS = """
                INSERT INTO card_topics (korean_card_id, topic_id)
                VALUES (?, ?);
            """;

    @Transactional
    @Override
    public void batchInsert(List<String[]> rows) {
        batchInsertTopic();
        int batchSize = 256;
        for(int i=0; i< rows.size();i+=batchSize){
            int end = Math.min(i+batchSize, rows.size());
            List<CardTopicRow> cardTopicRows = new ArrayList<>();
            for(int j=i;j<end;j++){
                convert(j, rows.get(j), cardTopicRows);
            }
            batchInsertCardTopic(cardTopicRows);
        }
    }

    private void batchInsertTopic(){
        CardTopicEnums[] topics = CardTopicEnums.values();
        jdbcTemplate.batchUpdate(INSERT_TOPICS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, topics[i].toString());
            }

            @Override
            public int getBatchSize() {
                return topics.length;
            }
        });
    }

    private void convert(int i, String[] row, List<CardTopicRow> topicRows){
        String topics = row[15];
        if (topics == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(topics, ",");
        while (st.hasMoreTokens()){
            String topic = st.nextToken();
            CardTopicEnums topicId = CardTopicEnums.fromString(topic);
            if(topicId != null){
                topicRows.add(new CardTopicRow((long) (i+1), topicId.toString()));
            }
        }
    }

    private void batchInsertCardTopic(List<CardTopicRow> rows){
        jdbcTemplate.batchUpdate(INSERT_CARD_TOPICS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, rows.get(i).getCardId());
                ps.setString(2, rows.get(i).getTopicId());
            }

            @Override
            public int getBatchSize() {
                return rows.size();
            }
        });
    }

    @Override
    public boolean isSupport(String type) {
        return true;
    }

    @Getter
    private static class CardTopicRow{
        private Long cardId;
        private String topicId;

        public CardTopicRow(Long cardId, String topicId) {
            this.cardId = cardId;
            this.topicId = topicId;
        }
    }

}
