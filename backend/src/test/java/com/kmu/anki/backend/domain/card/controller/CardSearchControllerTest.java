package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.PageParameters;
import com.kmu.anki.backend.domain.card.docs.KoreanCardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.parameters.CardParameters;
import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.service.CardSearchService;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
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
        KoreanCardDto koreanCardDto = new KoreanCardDto(1L, "검색어", "1", CardLevel.easy, Set.of(CardTopicEnums.CULTURE));
        Page<KoreanCardDto> result = new PageImpl<>(Collections.singletonList(koreanCardDto), PageRequest.of(0, 20), 1);
        given(cardSearchService.searchKoreanCardByKoreanWord("검색어", 0, 20)).willReturn(result);

        mockMvc.perform(get("/cards/korean-search")
                .param("query", "검색어")
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
                                                .summary("덱에 포함된 카드 모음")
                                                .queryParameters(
                                                        CardParameters.query,
                                                        PageParameters.page,
                                                        PageParameters.pageSize
                                                )
                                                .responseFields(
                                                        KoreanCardDtoDocs.koreanCardDtos
                                                )
                                                .responseSchema(KoreanCardDtoDocs.koreanCardSchemas)
                                                .build()
                                )
                        )
                );

    }
}