package com.kmu.anki.backend.domain.user.docs;

import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserOptionDtoDocs {
    public static final Schema userOptionSchema = new Schema("userOption");

    public static FieldDescriptor[] userOptionDto = userOptionDto("");

    public static FieldDescriptor[] userOptionDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"id").type(JsonFieldType.NUMBER).optional().description("user의 고유번호"),
                fieldWithPath(prefix+"dailyStudyWords").description("오늘 공부할 단어 수"),
                fieldWithPath(prefix+"dailyReviewWords").description("오늘 복습할 단어 수"),
                fieldWithPath(prefix+"languageCode").description("어떤 언어로 학습할지 그 언어코드"),
                fieldWithPath(prefix+"utcOffset").description("사용자의 UTC")
        };
    }

}
