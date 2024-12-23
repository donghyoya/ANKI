package com.kmu.anki.backend.domain.usercard.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.controller.CardDocs;
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

class UserDeckControllerTest extends AbstractControllerTest {

    @Test
    void getMyDeck() throws Exception{
        mockMvc.perform(
                get("/user/decks")
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyDecks")
                                                .summary("내가 현재 학습 중인 decks 보기")
                                                .responseFields(
                                                        BaseDocs.combine(
                                                                BaseDocs.basePageResponse(),
                                                                UserDeckDocs.userDeckDto(BaseDocs.basePageResponsePrefix)
                                                        )
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    void createStudyDeck() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("difficulty", CardDifficulty.easy);
        map.put("languageCode", LanguageCode.en);
        mockMvc.perform(
                post("/user/decks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(map))
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyDecks")
                                                .summary("학습을 시작함")
                                                .requestFields(
                                                        UserDeckDocs.studyRequestForm()
                                                )
                                                .responseFields(
                                                        UserDeckDocs.userDeckDto("")
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    void getDecksCard() throws Exception {
        mockMvc.perform(
                        get("/user/decks/{deckId}", 1)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("내가 현재 학습 중인 decks 보기")
                                                .pathParameters(
                                                        parameterWithName("deckId").description("userDeck의 고유번호")
                                                )
                                                .responseFields(
                                                        BaseDocs.combine(
                                                                BaseDocs.baseListResponse(),
                                                                UserDeckDocs.userCardDto(BaseDocs.basePageResponsePrefix)
                                                        )
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    void getDecksStudyCard() throws Exception {
        mockMvc.perform(
                        get("/user/decks/{deckId}/study", 1)
                                .param("studyType", "study")
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("내가 현재 학습 중인 decks 보기")
                                                .pathParameters(
                                                        parameterWithName("deckId").description("userDeck의 고유번호")
                                                )
                                                .queryParameters(
                                                        parameterWithName("studyType").description("study or review")
                                                )
                                                .responseFields(
                                                        BaseDocs.combine(
                                                                BaseDocs.baseListResponse(),
                                                                UserDeckDocs.userCardDto(BaseDocs.basePageResponsePrefix)
                                                        )
                                                )
                                                .build()
                                )
                        )
                );
    }
}