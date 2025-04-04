package com.kmu.anki.backend.security.auth.test.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTestControllerTest extends AbstractControllerTest {

    @Autowired private JwtTokenService jwtTokenService;

    @Test
    void authTest() throws Exception {
        // given
        String subject = "testUser";
        String role = "ROLE_ADMIN";
        Map<String, Object> claims = Map.of("role", role);
        String token = jwtTokenService.generateToken(subject, claims);

        // when & then
        mockMvc.perform(get("/auth-test")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(subject))
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("AuthTest")
                                                .summary("인증이 되는지 테스트하기 ")
                                                .responseFields(
                                                        fieldWithPath("username").description("token의 username")
                                                )
                                                .responseSchema(new Schema("AuthTestSchema"))
                                                .build()
                                )
                        )
                )
        ;
    }

    @Test
    void getTestToken() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/get-test-token"))
                .andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("AuthTest")
                                                .summary("jwt 인증 테스트용 토큰 발급")
                                                .responseFields(
                                                        fieldWithPath("token").description("test용 jwt token")
                                                )
                                                .responseSchema(new Schema("AuthTestTokenSchema"))
                                                .build()
                                )
                        )
                )
                .andReturn();
        Map<String, String> body = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Map.class);
        String token = body.get("token");
        assertTrue(jwtTokenService.validateToken(token));
    }
}