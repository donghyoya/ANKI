package com.kmu.anki.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
