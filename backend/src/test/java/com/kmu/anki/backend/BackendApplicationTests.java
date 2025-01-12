package com.kmu.anki.backend;

import com.kmu.anki.backend.global.listener.TestDataInsertListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:/data.sql", executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS, config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:/drop.sql", executionPhase= Sql.ExecutionPhase.AFTER_TEST_CLASS,  config = @SqlConfig(encoding = "UTF-8"))
//@TestExecutionListeners(
//		value = { TestDataInsertListener.class },
//		mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
//)
@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
