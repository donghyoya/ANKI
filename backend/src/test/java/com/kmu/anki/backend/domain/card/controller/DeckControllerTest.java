package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.docs.CardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.DeckDtoDocs;
import com.kmu.anki.backend.domain.card.docs.parameters.DeckParameters;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.auth.WithMockCustomOAuth2;
import com.kmu.anki.backend.global.ExceptionResponseDocs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeckControllerTest extends AbstractControllerTest {

    @WithMockCustomOAuth2
    @ParameterizedTest
    @EnumSource(QueryType.class)
    void getDecks(QueryType type) throws Exception {
        String identifier = String.format("{class-name}/{method-name}/%s", type.name());

        mockMvc.perform(
                get("/decks")
                        .param("queryType", type.name())
                        .session(session)
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier,
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

    @WithMockCustomOAuth2
    @Test
    void getDecks400() throws Exception {
        String identifier = String.format("{class-name}/{method-name}");

        mockMvc.perform(
                        get("/decks")
                                .param("queryType", "fail")
                ).andExpect(status().isBadRequest())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier,
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("검색어 조건에 맞는 Deck 보기")
                                                .queryParameters(
                                                        DeckParameters.queryType
                                                )
                                                .responseFields(
                                                        ExceptionResponseDocs.exceptionResponse
                                                )
                                                .responseSchema(ExceptionResponseDocs.exceptionResponseSchema)
                                                .build()
                                )
                        )
                )
        ;
    }


    @WithMockCustomOAuth2
    @ParameterizedTest
    @MethodSource("getDecksCardParams")
    void getDecksCard(String queryType, String query) throws Exception{
        String identifier = String.format("{class-name}/{method-name}/%s-%s", queryType, query);

        mockMvc.perform(
                get("/decks/cards")
                        .param("queryType", queryType)
                        .param("query",query)
                        .session(session)
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier,
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

    @WithMockCustomOAuth2
    @Test
    void getDecksCard400() throws Exception{
        String identifier = String.format("{class-name}/{method-name}");

        mockMvc.perform(
                        get("/decks/cards")
                                .param("queryType", "queryType")
                                .param("query","fail")
                ).andExpect(status().isBadRequest())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier,
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("덱에 포함된 카드 모음")
                                                .queryParameters(
                                                        DeckParameters.queryType,
                                                        DeckParameters.query
                                                )
                                                .responseFields(
                                                        ExceptionResponseDocs.exceptionResponse
                                                )
                                                .responseSchema(ExceptionResponseDocs.exceptionResponseSchema)
                                                .build()
                                )
                        )
                );
    }


    private static Stream<Arguments> getDecksCardParams(){
        return Arrays.stream(QueryType.values())
                .flatMap(queryType -> {
                            if(queryType == QueryType.level){
                                return Arrays.stream(CardLevel.values()).map(query->Arguments.of(queryType.toString(), query.toString()));
                            }else {
                                return Arrays.stream(CardTopicEnums.values()).map(query->Arguments.of(queryType.toString(), query.toString()));
                            }
                        }
                );
    }


}