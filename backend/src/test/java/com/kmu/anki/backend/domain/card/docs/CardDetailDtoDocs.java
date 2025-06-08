package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class CardDetailDtoDocs {
    /* Schema */

    public static final Schema cardDetailSchema = new Schema("cardDetail");
    public static final Schema cardDetailsSchema = new Schema("cardDetails");


    public static FieldDescriptor[] cardDetailDto = cardDetailDto("");

    public static FieldDescriptor[] cardDetailDtos = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            cardDetailDto(BaseDocs.basePageResponsePrefix)
    );


    public static FieldDescriptor[] cardDetailDto(String prefix){
        FieldDescriptor[] cardDetail = {
                fieldWithPath(prefix + "cardId").description("단어카드의 고유번호"),
                fieldWithPath(prefix + "koreanWord").description("한국어 단어"),
                fieldWithPath(prefix + "level").description("단어의 수준 (easy, normal, hard)"),
                fieldWithPath(prefix + "topics").type(JsonFieldType.ARRAY).description("카드의 분류"),
                fieldWithPath(prefix + "homographNumber")
                        .type(JsonFieldType.STRING)
                        .optional().description("동형어 번호"),
        };
        return BaseDocs.combine(cardDetail, cardMeaningWithForeign("meanings[]."));
    }

    public static FieldDescriptor[] cardMeaningWithForeign(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"originalLanguage")
                        .type(JsonFieldType.STRING)
                        .optional().description("단어의 원어"),
                fieldWithPath(prefix+"partsOfSpeech")
                        .type(JsonFieldType.STRING)
                        .optional().description("품사"),
                fieldWithPath(prefix+"pronunciation")
                        .type(JsonFieldType.STRING)
                        .optional().description("발음"),
                fieldWithPath(prefix+"relatedWords")
                        .type(JsonFieldType.STRING).optional().description("관련어"),
                fieldWithPath(prefix+"inflection")
                        .type(JsonFieldType.STRING)
                        .optional().description("활용"),
                fieldWithPath(prefix+"exampleUsage")
                        .type(JsonFieldType.STRING)
                        .optional().description("용례"),

                fieldWithPath(prefix+"languageCode").description("언어코드 (ISO 639-1)"),
                fieldWithPath(prefix+"foreignWord").description("외국어 표제어"),
                fieldWithPath(prefix+"foreignMeaning").description("외국어 뜻풀이"),
        };
    }
}
