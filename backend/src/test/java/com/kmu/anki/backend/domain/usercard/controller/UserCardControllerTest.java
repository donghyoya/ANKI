package com.kmu.anki.backend.domain.usercard.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.docs.CardDocs;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.service.UserCardService;
import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.BaseDocs;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserCardControllerTest extends AbstractControllerTest {
    @Autowired
    private UserCardService userCardService;

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
                                                        parameterWithName("id").description("카드의 고유번호")
                                                )
                                                .responseFields(
                                                        UserCardDocs.cardStudyDto("")
                                                )
                                                .responseSchema(UserCardDocs.cardStudySchema)
                                                .build()
                                )
                        )
                );
    }


    @Test
    void putUserCards() throws Exception {
        Page<UserCardDto> userCardDtos = userCardService.readStudyUserCard(1L, LanguageCode.en, CardLevel.easy);
        Long userCardId = userCardDtos.getContent().get(0).getUserCardId();

        HashMap<String, Object> map = new HashMap<>();
        map.put("nextStudyDate", LocalDateTime.now());
        map.put("lapses", 5);
        map.put("lastReview", LocalDateTime.now());
        map.put("reps", 72);
        map.put("scheduledDays", 0.9);
        map.put("stability", 1.3);
        map.put("state", CardState.Review);

        mockMvc.perform(
                        post("/cards/{id}/study", userCardId)
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
                                                        parameterWithName("id").description("card 고유번호")
                                                )
                                                .requestFields(
                                                        UserCardDocs.studyCardForm()
                                                )
                                                .requestSchema(UserCardDocs.studyCardFormSchema)
                                                .responseFields(
                                                        UserCardDocs.cardStudyDto("")
                                                )
                                                .responseSchema(UserCardDocs.cardStudySchema)
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
                                .param("queryType", "level")
                                .param("query","easy")
                ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("StudyCards")
                                                .summary("오늘 공부할 카드 모음 ")
                                                .queryParameters(
                                                        parameterWithName("studyType").description("study냐 review냐"),
                                                        parameterWithName("queryType").description("의미에 따른 분류인가 / 난이도에 따른 분류인가"),
                                                        parameterWithName("query").description("검색어 (level 또는 meaningGroup)")
                                                )
                                                .responseFields(
                                                        BaseDocs.combine(
                                                                BaseDocs.basePageResponse(),
                                                                UserCardDocs.userCardDto(BaseDocs.basePageResponsePrefix)
                                                        )
                                                )
                                                .responseSchema(UserCardDocs.userCardsSchema)
                                                .build()
                                )
                        )
                );
    }

}