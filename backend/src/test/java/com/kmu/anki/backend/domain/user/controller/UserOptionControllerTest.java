package com.kmu.anki.backend.domain.user.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.docs.UserOptionDtoDocs;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.auth.WithMockCustomOAuth2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserOptionControllerTest extends AbstractControllerTest {

    @WithMockCustomOAuth2
    @Test
    void getUserOption() throws Exception {
        mockMvc.perform(
                get("/user/option")
                        .session(session)
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Users")
                                                .responseFields(
                                                    UserOptionDtoDocs.userOptionDto
                                                )
                                                .responseSchema(
                                                        UserOptionDtoDocs.userOptionSchema
                                                )
                                                .build()
                                )
                        )
                );
    }

    @WithMockCustomOAuth2
    @ParameterizedTest
    @EnumSource(LanguageCode.class)
    void putUserOption(LanguageCode code) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("todayStudyWords", 30);
        map.put("todayReviewWords", 30);
        map.put("languageCode", code);
        mockMvc.perform(
                post("/user/option")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(map))
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}/"+code.name(),
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Users")
                                                .requestFields(
                                                        UserOptionDtoDocs.userOptionDto
                                                )
                                                .requestSchema(UserOptionDtoDocs.userOptionSchema)
                                                .responseFields(
                                                        UserOptionDtoDocs.userOptionDto
                                                )
                                                .responseSchema(UserOptionDtoDocs.userOptionSchema)
                                            .build()
                                )
                        )
                );
    }
}