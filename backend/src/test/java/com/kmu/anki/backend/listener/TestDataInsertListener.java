package com.kmu.anki.backend.listener;

import com.kmu.anki.backend.csv.importer.dto.CsvExtractResult;
import com.kmu.anki.backend.csv.importer.extractor.impl.CardCsvExtractor;
import com.kmu.anki.backend.csv.importer.repository.impl.CardCsvBatchInsertRepository;
import com.kmu.anki.backend.csv.importer.repository.impl.CardTopicBatchInsertRepository;
import com.kmu.anki.backend.csv.importer.repository.impl.ForeignCardCsvBatchInsertRepository;
import com.kmu.anki.backend.file.access.FileSystemAccessObject;
import com.kmu.anki.backend.file.access.impl.LocalFileSystemAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.testcontainers.utility.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("insert")
@SpringBootTest
public class TestDataInsertListener extends AbstractTestExecutionListener {
    @Autowired
    private CardCsvExtractor cardCsvExtractor;
    @Autowired private FileSystemAccessObject fileSAO;
    @Autowired private CardCsvBatchInsertRepository cardCsvBatchInsertRepository;
    @Autowired private ForeignCardCsvBatchInsertRepository foreignCardCsvBatchInsertRepository;
    @Autowired private CardTopicBatchInsertRepository cardTopicBatchInsertRepository;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        cardCsvExtractor = applicationContext.getBean(CardCsvExtractor.class);
        fileSAO = new LocalFileSystemAccessObject();
        cardCsvBatchInsertRepository = applicationContext.getBean(CardCsvBatchInsertRepository.class);
        foreignCardCsvBatchInsertRepository = applicationContext.getBean(ForeignCardCsvBatchInsertRepository.class);
        cardTopicBatchInsertRepository = applicationContext.getBean(CardTopicBatchInsertRepository.class);
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

        Resource data = fileSAO.load("insert_data.csv");
        CsvExtractResult extract = cardCsvExtractor.extract(data);
        cardCsvBatchInsertRepository.batchInsert(extract.getValidRecords());
        foreignCardCsvBatchInsertRepository.batchInsert(extract.getValidRecords());
        cardTopicBatchInsertRepository.batchInsert(extract.getValidRecords());
        jdbcTemplate.update("""
            insert into users(user_id, today_study_words) values (1, 200);
            insert into users(user_id, today_study_words) values (2, 200);
            
            INSERT INTO user_cards(korean_card_id, user_id, user_card_state)
            SELECT korean_cards.korean_card_id, 1, 'New'
            FROM korean_cards;
        """);

        super.beforeTestClass(testContext);
    }
}
