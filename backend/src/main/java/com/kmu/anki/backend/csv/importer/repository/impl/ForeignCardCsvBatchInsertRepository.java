package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.csv.importer.repository.CsvBatchInsertRepository;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ForeignCardCsvBatchInsertRepository implements CsvBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    private String INSERT_FOREIGN_CARD = """
                insert into foreign_cards(korean_card_id, language_code, foreign_word, foreign_meaning)
                values (?, ?, ?, ?);
            """;

    @Transactional
    @Override
    public void batchInsert(List<String[]> rows) {
        int batchSize = 256;
        for(int i=0;i< rows.size();i +=batchSize){
            int end = Math.min(i+ batchSize, rows.size());
            List<ForeignCardRow> foreignCardRows = new ArrayList<>();
            for(int j=i;j<end;j++){
                convert(j, rows.get(j), foreignCardRows);
            }
            batchInsertForeignCard(foreignCardRows);
        }
    }

    private void convert(int i, String[] row, List<ForeignCardRow> container){
        /**
         *  25 몽골어
         *  27 아랍어
         *  29 중국어
         *  31 베트남어
         *  33 태국어
         *  35 인도네시아어
         *  37 러시아어
         *  39 영어
         *  41 일본어
         *  43 프랑스어
         *  45 스페인어
         */
        Long koreanId = (long) i;
        LanguageCode[] values = LanguageCode.values();
        int wordNum = 25;
        for(LanguageCode code : values){
            container.add(
                    new ForeignCardRow(koreanId, code, row[wordNum], row[wordNum+1])
            );
            wordNum += 2;
        }
    }

    private void batchInsertForeignCard(List<ForeignCardRow> rows){
        jdbcTemplate.batchUpdate(INSERT_FOREIGN_CARD, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ForeignCardRow row = rows.get(i);
                ps.setLong(1, row.getKoreanId());
                ps.setString(2, row.getCode());
                ps.setString(3, row.getForeignWord());
                ps.setString(4, row.getForeignMeaning());
            }

            @Override
            public int getBatchSize() {
                return rows.size();
            }
        });

    }

    @Override
    public boolean isSupport(String type) {
        return "TYPE".equals(type);
    }

    @Getter
    private static class ForeignCardRow{
        private Long koreanId;
        private String code;
        private String foreignWord;
        private String foreignMeaning;

        public ForeignCardRow(Long koreanId, LanguageCode code, String foreignWord, String foreignMeaning) {
            this.koreanId = koreanId+1;
            this.code = code.toString();
            this.foreignWord = foreignWord;
            this.foreignMeaning = foreignMeaning;
        }
    }
}
