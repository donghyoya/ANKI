package com.kmu.anki.backend;

import com.kmu.anki.backend.domain.auth.legacy.service.OAuth2AccessTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:/test-data/test-data-insert.sql", executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS, config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:/test-data/test-data-drop.sql", executionPhase= Sql.ExecutionPhase.AFTER_TEST_CLASS,  config = @SqlConfig(encoding = "UTF-8"))
//@TestExecutionListeners(
//		value = { TestDataInsertListener.class },
//		mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
//)
@SpringBootTest
class BackendApplicationTests {

	@MockitoBean
	private OAuth2AccessTokenService oAuth2AccessTokenService;

	@MockitoBean
	private ClientRegistrationRepository clientRegistrationRepository;

	@Test
	void contextLoads() {
	}

}
