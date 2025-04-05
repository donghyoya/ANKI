package com.kmu.anki.backend.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureMockMvc
public abstract class AbstractControllerTest extends AbstractIntegrationTest{

    @Autowired protected JwtTokenService jwtTokenService;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    protected String token;

    protected MockHttpSession session = new MockHttpSession();

    @BeforeEach
    void setUp(final WebApplicationContext context, final RestDocumentationContextProvider restDocumentation){
        session.setAttribute("todayStudyWords", 20);
        session.setAttribute("todayReviewWords", 20);
        session.setAttribute("languageCode", LanguageCode.en);

        generateToken();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .apply(springSecurity())
                .alwaysDo(MockMvcResultHandlers.print())
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    /**
     * JWT 인증을 위해서
     */
    void generateToken(){
        // given
        String subject = "1";
        String role = "ROLE_ADMIN";
        Map<String, Object> claims = Map.of("role", role);
        token = jwtTokenService.generateToken(subject, claims);
    }
}
