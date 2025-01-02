package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.csv.importer.repository.CsvBatchInsertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CardCsvBatchInsertRepository implements CsvBatchInsertRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

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
             :korean_card_id,
             :korean_word,
             :homograph_number,
             :parts_of_speech,
             :original_language,
             :pronunciation,
             :inflection,
             :level,
             :related_words,
             :example_usage
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
    }

    @Override
    public boolean isSupport(String type) {
        return false;
    }
}
