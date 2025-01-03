package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.csv.importer.repository.CsvBatchInsertRepository;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CardCsvBatchInsertRepository implements CsvBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_KOREAN_CARD = """
        insert into korean_cards(
                                 korean_card_id,
                                 korean_word,
                                 homograph_number,
                                 parts_of_speech,
                                 original_language,
                                 pronunciation,
                                 inflection,
                                 level,
                                 related_words,
                                 example_usage
                                 )
        values (
             ?, --korean_card_id,
             ?, --korean_word,
             ?, --homograph_number,
             ?, --parts_of_speech,
             ?, --original_language,
             ?, --pronunciation,
             ?, --inflection,
             ?, --level,
             ?, --related_words,
             ? -- example_usage
        );
    """;

    @Override
    public void batchInsert(List<String[]> rows) {
        /**
         *  0: ID korean_card_id
         *  1: 표제어 koreanWord
         *  2: 동형어번호 homographNumber
         *  4 : 품사 partsOfSpeech
         *  6 : 원어 originalLanguage
         *  7 : 발음 pronunciation
         *  8 : 활용 inflection
         *  11: 어휘등급 level
         *  16: groups
         *  19 : 관련어 relatedWords
         *  24 : 용례 exampleUsage
         */
        jdbcTemplate.batchUpdate(INSERT_KOREAN_CARD, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String[] row = rows.get(i);
                ps.setLong(1,i+1);
                ps.setString(2, row[1]);
                ps.setString(3, row[2]);
                ps.setString(4, row[4]);
                ps.setString(5, row[6]);
                ps.setString(6, row[7]);
                ps.setString(7, row[8]);
                ps.setString(8, CardLevel.fromCsv(row[11]));
                ps.setString(9, row[19]);
                ps.setString(10, row[24]);
            }

            @Override
            public int getBatchSize() {
                return rows.size();
            }
        });

    }

    @Override
    public boolean isSupport(String type) {
        return false;
    }
}
