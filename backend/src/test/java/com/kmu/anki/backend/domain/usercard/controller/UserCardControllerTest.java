package com.kmu.anki.backend.domain.usercard.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.docs.CardDocs;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.BaseDocs;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserCardControllerTest extends AbstractControllerTest {

    @Test
    void getUserCards() throws Exception {
        mockMvc.perform(
                        get("/cards/{id}", 1)
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("userCard 보기")
                                                .pathParameters(
                                                        parameterWithName("id").description("userCard 고유번호")
                                                )
                                                .responseFields(
                                                        UserDeckDocs.userCardDto("")
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    void putUserCards() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("nextStudyDate", LocalDateTime.now());
        map.put("lapses", 5);
        map.put("lastReview", LocalDateTime.now());
        map.put("reps", 72);
        map.put("scheduledDays", 0.9);
        map.put("stability", 1.3);
        map.put("state", CardState.Review);

        mockMvc.perform(
                        post("/cards/{id}", 1)
                                .contentType("application/json")
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
                                                        parameterWithName("id").description("userCard 고유번호")
                                                )
                                                .requestFields(
                                                        UserDeckDocs.studyCardForm()
                                                )
                                                .responseFields(
                                                        UserDeckDocs.userCardDto("")
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    void getStudyCard() throws Exception{
        mockMvc.perform(
                        get("/cards/study")
                                .param("studyType", StudyType.study.toString())
                                .param("queryType", "difficulty")
                                .param("query","easy")
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Cards")
                                                .summary("오늘 공부할 카드 모음 ")
                                                .queryParameters(
                                                        parameterWithName("studyType").description("study냐 review냐"),
                                                        parameterWithName("queryType").description("의미에 따른 분류인가 / 난이도에 따른 분류인가"),
                                                        parameterWithName("query").description("검색어 (difficulty 또는 meaningGroup)")
                                                )
                                                .responseFields(
                                                        BaseDocs.combine(
                                                                BaseDocs.basePageResponse(),
                                                                UserDeckDocs.userCardDto(BaseDocs.basePageResponsePrefix)
                                                        )
                                                )
                                                .responseSchema(CardDocs.cardsSchema)
                                                .build()
                                )
                        )
                );
    }

}