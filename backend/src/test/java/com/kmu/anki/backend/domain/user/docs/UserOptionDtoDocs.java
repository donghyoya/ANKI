package com.kmu.anki.backend.domain.user.controller;

import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserOptionDtoDocs {
    public static final Schema userOptionSchema = new Schema("userOption");

    public static FieldDescriptor[] userOptionDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"id").description("user의 고유번호"),
                fieldWithPath(prefix+"todayStudyWords").description("오늘 공부할 단어 수"),
                fieldWithPath(prefix+"todayReviewWords").description("오늘 복습할 단어 수"),
                fieldWithPath(prefix+"languageCode").description("어떤 언어로 학습할지 그 언어코드")
        };
    }

}
