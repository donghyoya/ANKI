package com.kmu.anki.backend.domain.usercard.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.BaseDocs;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserCardControllerTest extends AbstractControllerTest {

    @Test
    void getUserCards() throws Exception {
        mockMvc.perform(
                get("/user/cards/{id}", 1)
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("userCard 보기")
                                                .pathParameters(
                                                        parameterWithName("id").description("userCard 고유번호")
                                                )
                                                .responseFields(
                                                        UserDeckDocs.userCardDto("")
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    void putUserCards() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("difficulty", CardDifficulty.easy);
        map.put("languageCode", LanguageCode.en);
        mockMvc.perform(
                        post("/user/cards/{id}", 1)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(map))
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("userCard 보기")
                                                .pathParameters(
                                                        parameterWithName("id").description("userCard 고유번호")
                                                )
                                                .requestFields(
                                                        UserDeckDocs.studyRequestForm()
                                                )
                                                .responseFields(
                                                        UserDeckDocs.userCardDto("")
                                                )
                                                .build()
                                )
                        )
                );

    }
}