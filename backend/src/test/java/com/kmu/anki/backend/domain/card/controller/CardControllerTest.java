package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.docs.CardDocs;
import com.kmu.anki.backend.domain.card.docs.CardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.CardParameters;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerTest extends AbstractControllerTest {

    @Test
    void getCard() throws Exception{
        mockMvc.perform(
                        get("/cards/{id}", 1)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Cards")
                                                .summary("Card 보기")
                                                .pathParameters(
                                                        CardParameters.cardId
                                                )
                                                .responseFields(
                                                        CardDtoDocs.card
                                                )
                                                .responseSchema(CardDtoDocs.cardSchema)
                                                .build()
                                )
                        )
                );
    }

    @Test
    void getCardDetails() throws Exception {
        mockMvc.perform(
                        get("/cards/{id}/details", 1)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Cards")
                                                .summary("Card에 대한 디테일한 정보 보기")
                                                .pathParameters(
                                                        CardParameters.cardId
                                                )
                                                .responseFields(
                                                        CardDocs.cardDetailDto("")
                                                )
                                                .responseSchema(CardDocs.cardDetailSchema)
                                                .build()
                                )
                        )
                );

    }
}