package com.kmu.anki.backend.domain.card.controller;

import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class CardDocs {
    public static FieldDescriptor[] cardDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"size").description("content의 크기"),
                fieldWithPath(prefix+"content").description("실제 데이터들"),
                fieldWithPath(prefix+"page").description("현재 페이지의 번호"),
                fieldWithPath(prefix+"pageSize").description("전체 페이지의 크기")
        };
    }

    public static FieldDescriptor[] deckDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"category").description("카드 분류"),
                fieldWithPath(prefix+"languageCode").description("언어코드"),
                fieldWithPath(prefix+"cardCounts").description("덱에 포함된 카드 개수"),
        };
    }
}
