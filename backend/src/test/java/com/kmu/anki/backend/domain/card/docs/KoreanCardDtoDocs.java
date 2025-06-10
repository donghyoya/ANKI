package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class KoreanCardDtoDocs {
    public static final Schema koreanCardSchema = new Schema("koreanCard");
    public static final Schema koreanCardSchemas = new Schema("koreanCards");

    public static final Schema koreanCardWithForeignWordSchema = new Schema("koreanCardWithForeignWord");
    public static final Schema koreanCardWithForeignWordSchemas = new Schema("koreanCardWithForeignWords");

    public static FieldDescriptor[] koreanCardDto = koreanCardDtoDocs("");

    public static FieldDescriptor[] koreanCardDtos = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            koreanCardDtoDocs(BaseDocs.basePageResponsePrefix)
    );

    public static FieldDescriptor[] koreanCardDtoDocs(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"cardId").type(JsonFieldType.NUMBER).optional().description("단어카드의 고유번호"),
                fieldWithPath(prefix+"koreanWord").type(JsonFieldType.STRING).optional().description("한국어 단어"),
                fieldWithPath(prefix+"level").type(JsonFieldType.STRING).optional().description("단어의 수준 (easy, normal, hard)"),
                fieldWithPath(prefix+"topics").type(JsonFieldType.STRING).optional().type(JsonFieldType.ARRAY).description("카드의 분류"),
                fieldWithPath(prefix+"homographNumber")
                        .type(JsonFieldType.STRING)
                        .optional().description("동형어 번호"),
        };
    }

    public static FieldDescriptor[] koreanCardWithForeignWordDtos = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            koreanCardWithForeignWordDocs(BaseDocs.basePageResponsePrefix)
    );

    public static FieldDescriptor[] koreanCardWithForeignWordDocs(String prefix){
        FieldDescriptor[] foreignWords = {
                fieldWithPath(prefix + "foreignWords").optional().type(JsonFieldType.ARRAY).description("외국어 단어")
        };
        return BaseDocs.combine(
                koreanCardDtoDocs(prefix),
                foreignWords
        );
    }

}
