package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.TestcontainersConfiguration;
import com.kmu.anki.backend.csv.importer.dto.CsvExtractResult;
import com.kmu.anki.backend.csv.importer.extractor.impl.CardCsvExtractor;
import com.kmu.anki.backend.file.access.FileSystemAccessObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

//@Import(TestcontainersConfiguration.class)
@ActiveProfiles("insert")
@SpringBootTest
class CardCsvBatchInsertRepositoryTest {
    @Autowired private CardCsvExtractor cardCsvExtractor;
    @Autowired private FileSystemAccessObject fileSAO;
    @Autowired private CardCsvBatchInsertRepository cardCsvBatchInsertRepository;

    @Test
    void batchInsert() {
        Resource data = fileSAO.load("insert_data.csv");
        CsvExtractResult extract = cardCsvExtractor.extract(data);
        cardCsvBatchInsertRepository.batchInsert(extract.getValidRecords());
    }
}