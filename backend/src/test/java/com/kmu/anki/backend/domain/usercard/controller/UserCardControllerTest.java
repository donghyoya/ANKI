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
import com.kmu.anki.backend.global.BaseDocs;
import com.kmu.anki.backend.global.auth.WithMockCustomOAuth2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static org.assertj.core.api.Fail.fail;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserCardControllerTest extends AbstractControllerTest {
    @Autowired
    private UserCardService userCardService;

    @WithMockCustomOAuth2
    @Test
    void getCardStudyInfo() throws Exception{
        mockMvc.perform(
                        get("/cards/{id}/study", 1)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("Card 학습 정보 보기")
                                                .pathParameters(
                                                        CardParameters.cardId
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

    @WithMockCustomOAuth2
    @Test
    void putUserCards() throws Exception {
        Page<UserCardDto> userCardDtos = userCardService.readStudyUserCard(1L, LanguageCode.en, CardLevel.easy);
        Long userCardId = userCardDtos.getContent().get(0).getUserCardId();

        HashMap<String, Object> map = new HashMap<>();
        map.put("due", LocalDateTime.now());
        map.put("lapses", 5);
        map.put("lastReview", LocalDateTime.now());
        map.put("reps", 72);
        map.put("scheduledDays", 0.9);
        map.put("stability", 1.3);
        map.put("state", CardState.Review);

        mockMvc.perform(
                        post("/cards/{id}/study", userCardId)
                                .contentType("application/json")
                                .session(session)
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
                                                        CardParameters.cardId
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

    @WithMockCustomOAuth2
    @ParameterizedTest
    @MethodSource("getStudyCardParams")
    void getStudyCard(String studyType, String queryType, String query) throws Exception{
        String identifier = String.format("{class-name}/{method-name}/%s-%s-%s", studyType, queryType, query);
        mockMvc.perform(
                        get("/cards/study")
                                .param("studyType", studyType)
                                .param("queryType", queryType)
                                .param("query",query)
                                .session(session)
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

}