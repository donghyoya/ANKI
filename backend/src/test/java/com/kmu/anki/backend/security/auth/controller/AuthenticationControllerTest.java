package com.kmu.anki.backend.security.auth.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationControllerTest extends AbstractControllerTest {

    @Test
    void getAuthToken() throws Exception {
        String subject = "1";
        String role = "ROLE_AUTHENTICATION";
        Map<String, Object> claims = Map.of("role", role);
        String token = jwtTokenService.generateAuthenticationToken(subject, claims);

        // when & then
        mockMvc.perform(get("/auth/token")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Authentication")
                                                .summary("실제 사용하는 토큰 발급하기. 단 authenticationToken, refreshToken만 사용할 것")
                                                .responseFields(
                                                        fieldWithPath("accessToken").description("accessToken"),
                                                        fieldWithPath("refreshToken").description("refreshToken"),
                                                        fieldWithPath("firstLogin").description("userOption을 바탕으로 최초 로그인 여부를 판별함")
                                                )
                                                .responseSchema(new Schema("TokenProviderSchema"))
                                                .build()
                                )
                        )
                )
        ;

    }
}