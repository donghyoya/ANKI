package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.BaseDocs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeckControllerTest extends AbstractControllerTest {

    @Test
    void getDecks() throws Exception {
        mockMvc.perform(
                get("/decks")
                        .param("languageCode", "en")
                        .param("queryType", "difficulty")
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("검색어 조건에 맞는 Deck 보기")
                                                .queryParameters(
                                                        parameterWithName("languageCode").description("언어코드"),
                                                        parameterWithName("queryType").description("의미에 따른 분류인가 / 난이도에 따른 분류인가")
                                                )
                                                .responseFields(
                                                        BaseDocs.combine(
                                                                BaseDocs.basePageResponse(),
                                                                CardDocs.deckDto(BaseDocs.basePageResponsePrefix)
                                                        )
                                                )
                                                .build()
                                )
                                )
                        )
        ;
    }

    @Test
    void getDecksCard() throws Exception{
        mockMvc.perform(
                get("/decks/cards")
                        .param("languageCode", "en")
                        .param("queryType", "difficulty")
                        .param("query","easy")
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Cards")
                                                .summary("덱에 포함된 카드 모음")
                                                .queryParameters(
                                                        parameterWithName("languageCode").description("언어코드"),
                                                        parameterWithName("queryType").description("의미에 따른 분류인가 / 난이도에 따른 분류인가"),
                                                        parameterWithName("query").description("검색어 (difficulty 또는 meaningGroup)")
                                                )
                                                .responseFields(
                                                        BaseDocs.combine(
                                                                BaseDocs.basePageResponse(),
                                                                CardDocs.cardDto(BaseDocs.basePageResponsePrefix)
                                                        )
                                                )
                                                .build()
                                )
                        )
                );
    }
}