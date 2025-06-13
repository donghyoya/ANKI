package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.PageParameters;
import com.kmu.anki.backend.domain.card.controller.option.ForeignCardSearchOption;
import com.kmu.anki.backend.domain.card.docs.ForeignCardSearchResultDocs;
import com.kmu.anki.backend.domain.card.docs.KoreanCardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.parameters.CardParameters;
import com.kmu.anki.backend.domain.card.dto.ForeignCardSearchResult;
import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.dto.KoreanCardWithForeignWord;
import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import com.kmu.anki.backend.domain.card.entity.KoreanMeaning;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.service.CardSearchService;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CardSearchControllerTest extends AbstractControllerTest {
    @MockitoBean
    private CardSearchService cardSearchService;
    @MockitoBean
    private UserOptionService userOptionService;

    @Test
    void searchKoreanCard() throws Exception {
        KoreanCardWithForeignWord koreanCardDto = new KoreanCardWithForeignWord(1L, "검색어", "1", CardLevel.easy, Set.of(CardTopicEnums.CULTURE), List.of("ENGLISH"));
        Page<KoreanCardWithForeignWord> result = new PageImpl<>(Collections.singletonList(koreanCardDto), PageRequest.of(0, 20), 1);
        given(cardSearchService.searchKoreanCardByKoreanWord("검색어", LanguageCode.en,0, 20)).willReturn(result);

        mockMvc.perform(get("/cards/korean-search")
                .param("query", "검색어")
                        .param("code", "en")
                        .param("page", "1")
                        .param("pageSize", "20")
        )
                .andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("CardSearch")
                                                .summary("한국어 검색결과")
                                                .queryParameters(
                                                        CardParameters.query,
                                                        CardParameters.languageCode,
                                                        PageParameters.page,
                                                        PageParameters.pageSize
                                                )
                                                .responseFields(
                                                        KoreanCardDtoDocs.koreanCardWithForeignWordDtos
                                                )
                                                .responseSchema(KoreanCardDtoDocs.koreanCardWithForeignWordSchemas)
                                                .build()
                                )
                        )
                );

    }

    @Test
    void searchForeignWord() throws Exception {
        ForeignCard foreignCard = ForeignCard.builder()
                .id(1L)
                .foreignWord("query")
                .foreignMeaning("query")
                .build();
        KoreanMeaning koreanMeaning = KoreanMeaning.builder()
                .id(1L)
                .exampleUsage("용례")
                .originalLanguage("원어")
                .partsOfSpeech("품사")
                .pronunciation("발음")
                .relatedWords("관련어")
                .inflection("활용")
                .build();
        KoreanCard koreanCard = KoreanCard.builder()
                .id(1L)
                .koreanWord("한국어")
                .homographNumber("1")
                .level(CardLevel.easy)
                .build();

        koreanCard.addMeanings(koreanMeaning);
        koreanMeaning.addForeignCards(foreignCard);
        Page<ForeignCardSearchResult> result = new PageImpl<>(Collections.singletonList(ForeignCardSearchResult.of(foreignCard)), PageRequest.of(0, 20), 1);

        given(cardSearchService.searchForeignCard("query", ForeignCardSearchOption.BOTH, 0, 20)).willReturn(result);

        mockMvc.perform(get("/cards/foreign-search")
                        .param("query", "query")
                        .param("option", ForeignCardSearchOption.BOTH.name())
                        .param("page", "1")
                        .param("pageSize", "20")
                )
                .andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("CardSearch")
                                                .summary("외국어 검색결과")
                                                .queryParameters(
                                                        CardParameters.query,
                                                        CardParameters.foreignCardSearchOption,
                                                        PageParameters.page,
                                                        PageParameters.pageSize
                                                )
                                                .responseFields(
                                                        ForeignCardSearchResultDocs.foreignCardSearchResults
                                                )
                                                .responseSchema(ForeignCardSearchResultDocs.foreignCardSearchResultsSchema)
                                                .build()
                                )
                        )
                );

    }
}