package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.docs.CardDocs;
import com.kmu.anki.backend.domain.usercard.controller.UserCardDocs;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
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
                                                        parameterWithName("id").description("카드의 고유번호")
                                                )
                                                .responseFields(
                                                        CardDocs.cardDto("")
                                                )
                                                .responseSchema(CardDocs.cardSchema)
                                                .build()
                                )
                        )
                );
    }

}