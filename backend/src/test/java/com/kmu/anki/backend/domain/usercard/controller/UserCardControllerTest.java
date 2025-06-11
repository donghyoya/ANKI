package com.kmu.anki.backend.domain.usercard.controller;

import com.epages.restdocs.apispec.HeaderDescriptorWithType;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.docs.parameters.CardParameters;
import com.kmu.anki.backend.domain.card.docs.parameters.DeckParameters;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.docs.CardStudyDtoDocs;
import com.kmu.anki.backend.domain.usercard.docs.StudyCardFormDocs;
import com.kmu.anki.backend.domain.usercard.docs.UserCardDtoDocs;
import com.kmu.anki.backend.domain.usercard.docs.parameters.UserCardParameters;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.auth.WithMockCustomOAuth2;
import com.kmu.anki.backend.global.ExceptionResponseDocs;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserCardControllerTest extends AbstractControllerTest {
    @Autowired
    private UserCardService userCardService;

    @Test
    void getCardStudyInfo() throws Exception{
        Page<UserCardDto> userCardDtos = userCardService.readStudyUserCard(1L, LanguageCode.en, StudyType.study,CardLevel.easy);
        Long userCardId = userCardDtos.getContent().get(0).getUserCardId();

        mockMvc.perform(
                        get("/cards/{userCardId}/study", userCardId)
                                .header("Authorization", "Bearer " + token)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("Card 학습 정보 보기")
                                                .pathParameters(
                                                        CardParameters.userCardId
                                                )
                                                .responseFields(
                                                        CardStudyDtoDocs.cardStudyInfo
                                                )
                                                .responseSchema(CardStudyDtoDocs.cardStudySchema)
                                                .build()
                                )
                        )
                );
    }

    @Test
    void getCardStudyInfo404() throws Exception{
        mockMvc.perform(
                        get("/cards/{userCardId}/study", -1)
                                .header("Authorization", "Bearer " + token)
                ).andExpect(status().isNotFound())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("Card 학습 정보 보기")
                                                .pathParameters(
                                                        CardParameters.userCardId
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


    @Test
    void putUserCards() throws Exception {
        Page<UserCardDto> userCardDtos = userCardService.readStudyUserCard(1L, LanguageCode.en, StudyType.study,CardLevel.easy);
        Long userCardId = userCardDtos.getContent().get(0).getUserCardId();

        HashMap<String, Object> map = new HashMap<>();
        map.put("due", LocalDateTime.now());
        map.put("lapses", 5);
        map.put("lastReview", LocalDateTime.now());
        map.put("reps", 72);
        map.put("scheduledDays", 0.9);
        map.put("stability", 1.3);
        map.put("difficulty",1);
        map.put("state", CardState.Review.getPriority());

        mockMvc.perform(
                        post("/cards/{userCardId}/study", userCardId)
                                .contentType("application/json")
                                .header("Authorization", "Bearer " + token)
                                .content(objectMapper.writeValueAsString(map))
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("Card 학습결과를 갱신")
                                                .pathParameters(
                                                        CardParameters.userCardId
                                                )
                                                .requestFields(
                                                        StudyCardFormDocs.studyCardForm
                                                )
                                                .requestSchema(StudyCardFormDocs.studyCardFormSchema)
                                                .responseFields(
                                                        CardStudyDtoDocs.cardStudyInfo
                                                )
                                                .responseSchema(CardStudyDtoDocs.cardStudySchema)
                                                .build()
                                )
                        )
                );
    }

    @Test
    void putUserCards404() throws Exception {
        Page<UserCardDto> userCardDtos = userCardService.readStudyUserCard(1L, LanguageCode.en, StudyType.study,CardLevel.easy);
        Long userCardId = userCardDtos.getContent().get(0).getUserCardId();

        HashMap<String, Object> map = new HashMap<>();
        map.put("due", LocalDateTime.now());
        map.put("lapses", 5);
        map.put("lastReview", LocalDateTime.now());
        map.put("reps", 72);
        map.put("scheduledDays", 0.9);
        map.put("stability", 1.3);
        map.put("difficulty",1);
        map.put("state", CardState.Review.getPriority());

        mockMvc.perform(
                        post("/cards/{userCardId}/study", -1)
                                .contentType("application/json")
                                .header("Authorization", "Bearer " + token)
                                .content(objectMapper.writeValueAsString(map))
                ).andExpect(status().isNotFound())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("Card 학습결과를 갱신")
                                                .pathParameters(
                                                        CardParameters.userCardId
                                                )
                                                .requestFields(
                                                        StudyCardFormDocs.studyCardForm
                                                )
                                                .requestSchema(StudyCardFormDocs.studyCardFormSchema)
                                                .responseFields(
                                                        ExceptionResponseDocs.exceptionResponse
                                                )
                                                .responseSchema(ExceptionResponseDocs.exceptionResponseSchema)
                                                .build()
                                )
                        )
                );
    }


    @Order(1)
    @ParameterizedTest
    @MethodSource("getStudyCardParams")
    void getStudyCard(String studyType, String queryType, String query) throws Exception{
        String identifier = String.format("{class-name}/{method-name}/%s-%s-%s", studyType, queryType, query);
        mockMvc.perform(
                        get("/cards/study")
                                .param("studyType", studyType)
                                .param("queryType", queryType)
                                .param("query",query)
                                .header("Authorization", "Bearer " + token)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier, //"{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("오늘 공부할 카드 모음 ")
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
                );
    }

    @Disabled
    @Order(3)
    @Test
    void getStudyCardWithoutAuth() throws Exception{
        String identifier = String.format("{class-name}/{method-name}/without_auth");
        mockMvc.perform(
                        get("/cards/study")
                                .param("studyType", StudyType.study.toString())
                                .param("queryType", QueryType.level.toString())
                                .param("query",CardLevel.easy.toString())
                                .header("Authorization", "Bearer token")
                ).andExpect(status().isUnauthorized())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier, //"{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("오늘 공부할 카드 모음 ")
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
                );
    }

    @Order(2)
    @Test
    void getStudyCard400() throws Exception{
        String identifier = String.format("{class-name}/{method-name}");
        mockMvc.perform(
                        get("/cards/study")
                                .param("studyType", "studyType")
                                .param("queryType", "queryType")
                                .param("query","query")
                                .header("Authorization", "Bearer " + token)
                ).andExpect(status().isBadRequest())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier, //"{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("오늘 공부할 카드 모음 ")
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
                );
    }


    private static Stream<Arguments> getStudyCardParams(){
        return Arrays.stream(StudyType.values())
                .flatMap(studyType -> Arrays.stream(QueryType.values()).flatMap(
                        queryType -> {
                            if(queryType == QueryType.level){
                                return Arrays.stream(CardLevel.values()).map(query->Arguments.of(studyType.toString(), queryType.toString(), query.toString()));
                            }else {
                                return Arrays.stream(CardTopicEnums.values()).map(query->Arguments.of(studyType.toString(), queryType.toString(), query.toString()));
                            }
                        }
                ));
    }

    @Test
    void deleteCache() throws Exception {
        String identifier = String.format("{class-name}/{method-name}");
        mockMvc.perform(
                        get("/cards/delete-cache")
                                .param("studyType", StudyType.study.toString())
                                .param("queryType", QueryType.level.toString())
                                .param("query",CardLevel.easy.toString())
                                .header("Authorization", "Bearer " + token)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                identifier, //"{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("디버그용 캐시삭제 로직")
                                                .queryParameters(
                                                        UserCardParameters.studyType,
                                                        DeckParameters.queryType,
                                                        DeckParameters.query
                                                )
                                                .responseFields(
                                                        fieldWithPath("message").description("디버그용 메시지")
                                                )
                                                .build()
                                )
                        )
                );
    }
}