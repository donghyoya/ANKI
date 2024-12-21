package com.kmu.anki.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Sql(scripts = "/data.sql", executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/drop.sql", executionPhase= Sql.ExecutionPhase.AFTER_TEST_CLASS)
@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
