package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.docs.CardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.DeckDtoDocs;
import com.kmu.anki.backend.domain.card.docs.parameters.DeckParameters;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeckControllerTest extends AbstractControllerTest {

    @Test
    void getDecksByDifficulty() throws Exception {
        mockMvc.perform(
                get("/decks")
                        .param("queryType", "level")
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("검색어 조건에 맞는 Deck 보기")
                                                .queryParameters(
                                                        DeckParameters.queryType
                                                )
                                                .responseFields(
                                                        DeckDtoDocs.decks
                                                )
                                                .responseSchema(DeckDtoDocs.decksSceham)
                                                .build()
                                )
                                )
                        )
        ;
    }

    @Test
    void getDecksByMeaning() throws Exception {
        mockMvc.perform(
                        get("/decks")
                                .param("queryType", "meaning")
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("검색어 조건에 맞는 Deck 보기")
                                                .queryParameters(
                                                        DeckParameters.queryType
                                                )
                                                .responseFields(
                                                        DeckDtoDocs.decks
                                                )
                                                .responseSchema(DeckDtoDocs.decksSceham)
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
                        .param("queryType", "level")
                        .param("query","easy")
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("덱에 포함된 카드 모음")
                                                .queryParameters(
                                                        DeckParameters.queryType,
                                                        DeckParameters.query
                                                )
                                                .responseFields(
                                                        CardDtoDocs.cards
                                                )
                                                .responseSchema(CardDtoDocs.cardsSchema)
                                                .build()
                                )
                        )
                );
    }
}