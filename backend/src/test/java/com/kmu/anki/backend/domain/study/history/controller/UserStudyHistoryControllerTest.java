package com.kmu.anki.backend.domain.study.history.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.PageParameters;
import com.kmu.anki.backend.domain.card.controller.option.QueryType;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.study.history.dto.UserStudyHistoryDto;
import com.kmu.anki.backend.domain.study.history.service.UserStudyHistoryService;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.study.history.controller.docs.UserStudyHistoryDtoDocs;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserStudyHistoryControllerTest extends AbstractControllerTest {

    @MockitoBean
    private UserStudyHistoryService userStudyHistoryService; // MockBean으로 등록

    @Test
    void getUserHistory() throws Exception {
        List<UserStudyHistoryDto> historyDtos = Arrays.asList(
                UserStudyHistoryDto.builder()
                        .studyType(StudyType.study)
                        .deckType(QueryType.level)
                        .deckName(CardLevel.easy.toString())
                        .studyDate(LocalDateTime.now())
                        .build(),
                UserStudyHistoryDto.builder()
                        .studyType(StudyType.review)
                        .deckType(QueryType.level)
                        .deckName(CardLevel.easy.toString())
                        .studyDate(LocalDateTime.now())
                        .build(),
                UserStudyHistoryDto.builder()
                        .studyType(StudyType.study)
                        .deckType(QueryType.meaning)
                        .deckName(CardTopicEnums.FASHION_AND_APPEARANCE.name())
                        .studyDate(LocalDateTime.now())
                        .build()
        );

        Page<UserStudyHistoryDto> pageResult = new PageImpl<>(historyDtos, PageRequest.of(1, 20), historyDtos.size());
        when(userStudyHistoryService.readUserHistory(1L, 0, 20)).thenReturn(pageResult);

        mockMvc.perform(
                        get("/decks/history")
                                .param("page", "1")
                                .param("pageSize", "20")
                                .header("Authorization", "Bearer " + token)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("덱 학습 기록보기")
                                                .queryParameters(
                                                        PageParameters.page,
                                                        PageParameters.pageSize
                                                )
                                                .responseFields(
                                                        UserStudyHistoryDtoDocs.userStudyHistories
                                                )
                                                .responseSchema(UserStudyHistoryDtoDocs.userStudyHistoriesSchema)
                                                .build()
                                )
                        )
                )
        ;
    }

    @Test
    void getLatestStudy() throws Exception {
        List<UserStudyHistoryDto> historyDtos = Arrays.asList(
                UserStudyHistoryDto.builder()
                        .studyType(StudyType.study)
                        .deckType(QueryType.level)
                        .deckName(CardLevel.easy.toString())
                        .studyDate(LocalDateTime.now())
                        .build(),
                UserStudyHistoryDto.builder()
                        .studyType(StudyType.review)
                        .deckType(QueryType.level)
                        .deckName(CardLevel.easy.toString())
                        .studyDate(LocalDateTime.now())
                        .build(),
                UserStudyHistoryDto.builder()
                        .studyType(StudyType.study)
                        .deckType(QueryType.meaning)
                        .deckName(CardTopicEnums.FASHION_AND_APPEARANCE.name())
                        .studyDate(LocalDateTime.now())
                        .build()
        );

        Page<UserStudyHistoryDto> pageResult = new PageImpl<>(historyDtos, PageRequest.of(1, 20), historyDtos.size());
        when(userStudyHistoryService.readLatestDecks(1L)).thenReturn(historyDtos.getFirst());

        mockMvc.perform(
                        get("/decks/latest")
                                .header("Authorization", "Bearer " + token)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Decks")
                                                .summary("가장 최근 학습한 덱보기")
                                                .responseFields(
                                                        UserStudyHistoryDtoDocs.userStudyHistory
                                                )
                                                .responseSchema(UserStudyHistoryDtoDocs.userStudyHistorySchema)
                                                .build()
                                )
                        )
                )
        ;
    }
}