package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.PageParameters;
import com.kmu.anki.backend.domain.card.docs.CardDetailDtoDocs;
import com.kmu.anki.backend.domain.card.docs.CardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.parameters.CardParameters;
import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.ForeignCardRepository;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerTest extends AbstractControllerTest {
    private static ForeignCardRepository foreignCardRepository;

    @BeforeAll
    static void setUp(ApplicationContext context){
        foreignCardRepository = context.getBean(ForeignCardRepository.class);
    }

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
                                                        CardDetailDtoDocs.cardDetailDto
                                                )
                                                .responseSchema(CardDetailDtoDocs.cardDetailSchema)
                                                .build()
                                )
                        )
                );
    }

    @ParameterizedTest
    @MethodSource("getForeignSearch")
    void getForeignSearch(LanguageCode code, String query) throws Exception{
        String identifier = String.format("{class-name}/{method-name}/%s", query);

        mockMvc.perform(
                        get("/cards/foreign-search")
                                .param("code", code.name())
                                .param("query", query)
                                .param("page", "1")
                                .param("pageSize", "20")
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier,
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Cards")
                                                .summary("외국어로 카드 검색")
                                                .queryParameters(
                                                        CardParameters.languageCode,
                                                        CardParameters.query,
                                                        PageParameters.page,
                                                        PageParameters.pageSize
                                                )
                                                .responseFields(
                                                        CardDetailDtoDocs.cardDetailDtos
                                                )
                                                .responseSchema(CardDetailDtoDocs.cardDetailsSchema)
                                                .build()
                                )
                        )
                );

    }

    private static Stream<Arguments> getForeignSearch(){
        List<ForeignCard> all = foreignCardRepository.findAll();

        return all.stream().limit(20)
                .map(fc -> Arguments.of(fc.getLanguageCode(), fc.getForeignWord()));
    }

}