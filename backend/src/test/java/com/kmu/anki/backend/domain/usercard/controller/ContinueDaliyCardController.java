package com.kmu.anki.backend.domain.usercard.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.controller.option.QueryType;
import com.kmu.anki.backend.domain.card.docs.parameters.DeckParameters;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.docs.UserCardDtoDocs;
import com.kmu.anki.backend.domain.usercard.docs.parameters.UserCardParameters;
import com.kmu.anki.backend.domain.usercard.service.CheckDailyCardService;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.ExceptionResponseDocs;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ContinueDaliyCardController extends AbstractControllerTest {
    @MockitoBean
    private CheckDailyCardService checkDailyCardService;

    @Test
    void getStudyCardsContinue() throws Exception {
        String studyType = StudyType.study.toString();
        String queryType = QueryType.LEVEL.toString(); 
        String query = "easy";

        when(checkDailyCardService.checkLearingComplete(any(Long.class), any(LanguageCode.class), any(StudyType.class), any(CardLevel.class))).thenReturn(true);

        mockMvc.perform(get("/cards/study/continue")
                .param("studyType", studyType)
                .param("queryType", queryType)
                .param("query",query)
                .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("이어서 단어 공부하기")
                                                .queryParameters(
                                                        UserCardParameters.studyType,
                                                        DeckParameters.queryType,
                                                        DeckParameters.query
                                                )
                                                .responseFields(
                                                        UserCardDtoDocs.userCards
                                                )
                                                .responseSchema(UserCardDtoDocs.userCardsSchema)
                                                .build()
                                )
                        )
                );;
    }

    @Test
    void getStudyCardsContinueWithException() throws Exception {
        String studyType = StudyType.study.toString();
        String queryType = QueryType.LEVEL.toString();
        String query = "easy";

        when(checkDailyCardService.checkLearingComplete(any(Long.class), any(LanguageCode.class), any(StudyType.class), any(CardLevel.class))).thenReturn(false);

        mockMvc.perform(get("/cards/study/continue")
                        .param("studyType", studyType)
                        .param("queryType", queryType)
                        .param("query",query)
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("이어서 단어 공부하기")
                                                .queryParameters(
                                                        UserCardParameters.studyType,
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
                );;
    }

}