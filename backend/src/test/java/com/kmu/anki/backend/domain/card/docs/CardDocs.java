package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class CardDocs {
    public static final Schema cardSchema = new Schema("card");
    public static final Schema cardsSchema = new Schema("cards");
    public static final Schema deckSceham = new Schema("deck");
    public static final Schema decksSceham = new Schema("decks");

    public static FieldDescriptor[] cardDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"cardId").description("단어카드의 고유번호"),
                fieldWithPath(prefix+"koreanWord").description("한국어 단어"),
                fieldWithPath(prefix+"foreignWord").description("외국어 단어"),
                fieldWithPath(prefix+"difficulty").description("언어의 난이도 (easy, normal, hard)"),
                fieldWithPath(prefix+"languageCode").description("언어코드 (ISO 639-1)")
        };
    }

    public static FieldDescriptor[] deckDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"category").description("카드 분류"),
                fieldWithPath(prefix+"languageCode").description("언어코드"),
                fieldWithPath(prefix+"cardCounts").description("덱에 포함된 카드 개수"),
                fieldWithPath(prefix+"overdueRate").description("due가 지난 상태인 카드 비율"),
                fieldWithPath(prefix+"maturitiyRate").description("state가 review인 카드 비율:")
        };
    }
}
