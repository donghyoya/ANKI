package com.kmu.anki.backend.domain.usercard.controller;

import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserDeckDocs {
    public static FieldDescriptor[] userDeckDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"id").description("userDeck의 고유번호"),
                fieldWithPath(prefix+"counts").description("이 덱에 포함된 카드 숫자"),
                fieldWithPath(prefix+"difficulty").description("언어의 난이도 (easy, normal, hard)"),
                fieldWithPath(prefix+"languageCode").description("언어코드 (ISO 639-1)")
        };
    }

}
