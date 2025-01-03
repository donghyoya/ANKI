package com.kmu.anki.backend.global;

import com.kmu.anki.backend.TestcontainersConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:/data.sql", executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS, config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:/drop.sql", executionPhase= Sql.ExecutionPhase.AFTER_TEST_CLASS,  config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
public abstract class AbstractIntegrationTest {
}
