package com.kmu.anki.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
