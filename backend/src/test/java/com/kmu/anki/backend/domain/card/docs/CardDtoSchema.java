package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class CardDtoSchema {
    public static final Schema cardSchema = new Schema("card");
    public static final Schema cardsSchema = new Schema("cards");

    public static FieldDescriptor[] card = cardFields("");
    public static FieldDescriptor[] cards = cardFields(BaseDocs.basePageResponsePrefix);

    public static FieldDescriptor[] cardFields(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"cardId").description("단어카드의 고유번호"),
                fieldWithPath(prefix+"koreanWord").description("한국어 단어"),
                fieldWithPath(prefix+"foreignWord").description("외국어 단어"),
                fieldWithPath(prefix+"level").description("단어의 수준 (easy, normal, hard)"),
                fieldWithPath(prefix+"languageCode").description("언어코드 (ISO 639-1)")
        };
    }


}
