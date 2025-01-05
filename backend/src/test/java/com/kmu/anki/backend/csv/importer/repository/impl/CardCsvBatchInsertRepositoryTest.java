package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.TestcontainersConfiguration;
import com.kmu.anki.backend.csv.importer.dto.CsvExtractResult;
import com.kmu.anki.backend.csv.importer.extractor.impl.CardCsvExtractor;
import com.kmu.anki.backend.file.access.FileSystemAccessObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 원래는 controller로 구성하는게 옳지만 편의상 테스트 class로 처리함
 * 단 Disabled를 설정하여 test에는 문제가 없도록 조치하였음
 */
@Import(TestcontainersConfiguration.class)
//@Disabled
@ActiveProfiles("insert")
@SpringBootTest
class CardCsvBatchInsertRepositoryTest {
    @Autowired private CardCsvExtractor cardCsvExtractor;
    @Autowired private FileSystemAccessObject fileSAO;
    @Autowired private CardCsvBatchInsertRepository cardCsvBatchInsertRepository;
    @Autowired private ForeignCardCsvBatchInsertRepository foreignCardCsvBatchInsertRepository;
    @Autowired private CardTopicBatchInsertRepository cardTopicBatchInsertRepository;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Test
    void batchInsert() throws IOException {
        jdbcTemplate.update("truncate korean_cards cascade");
        Resource data = fileSAO.load("insert_data.csv");

        CsvExtractResult extract = cardCsvExtractor.extract(data);
        cardCsvBatchInsertRepository.batchInsert(extract.getValidRecords());
        foreignCardCsvBatchInsertRepository.batchInsert(extract.getValidRecords());
        cardTopicBatchInsertRepository.batchInsert(extract.getValidRecords());
    }
}