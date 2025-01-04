package com.kmu.anki.backend.domain.user.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.user.docs.UserOptionDtoDocs;
import com.kmu.anki.backend.global.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserOptionControllerTest extends AbstractControllerTest {

    @Test
    void getUserOption() throws Exception {
        mockMvc.perform(
                get("/user/option")
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Users")
                                                .responseFields(
                                                    UserOptionDtoDocs.userOptionDto
                                                )
                                                .responseSchema(
                                                        UserOptionDtoDocs.userOptionSchema
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    void putUserOption() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("todayStudyWords", 200);
        map.put("todayReviewWords", 200);
        map.put("languageCode", LanguageCode.en);
        mockMvc.perform(
                post("/user/option")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(map))
        ).andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "{class-name}/{method-name}",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("Users")
                                                .requestFields(
                                                        UserOptionDtoDocs.userOptionDto
                                                )
                                                .requestSchema(UserOptionDtoDocs.userOptionSchema)
                                                .responseFields(
                                                        UserOptionDtoDocs.userOptionDto
                                                )
                                                .responseSchema(UserOptionDtoDocs.userOptionSchema)
                                            .build()
                                )
                        )
                );
    }
}