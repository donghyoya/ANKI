package com.kmu.anki.backend.domain.card.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.PageParameters;
import com.kmu.anki.backend.domain.card.docs.KoreanCardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.parameters.CardParameters;
import com.kmu.anki.backend.domain.card.docs.parameters.DeckParameters;
import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.card.repository.CardSearchRepository;
import com.kmu.anki.backend.domain.card.service.CardSearchService;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.BaseDocs;
import com.kmu.anki.backend.global.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
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
                                                .tag("Decks")
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