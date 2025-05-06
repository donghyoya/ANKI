package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class KoreanCardDtoDocs {
    public static final Schema koreanCardSchema = new Schema("koreanCard");
    public static final Schema koreanCardSchemas = new Schema("koreanCards");


    public static FieldDescriptor[] koreanCardDto = cardDetailDto("");

    public static FieldDescriptor[] koreanCardDtos = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            cardDetailDto(BaseDocs.basePageResponsePrefix)
    );


    public static FieldDescriptor[] cardDetailDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"cardId").description("단어카드의 고유번호"),
                fieldWithPath(prefix+"koreanWord").description("한국어 단어"),
                fieldWithPath(prefix+"level").description("단어의 수준 (easy, normal, hard)"),
                fieldWithPath(prefix+"topics").type(JsonFieldType.ARRAY).description("카드의 분류"),
                fieldWithPath(prefix+"homographNumber")
                        .type(JsonFieldType.STRING)
                        .optional().description("동형어 번호"),
        };
    }
}
