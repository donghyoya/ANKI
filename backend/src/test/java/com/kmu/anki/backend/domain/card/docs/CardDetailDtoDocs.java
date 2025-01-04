package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class CardDetailDtoDocs {
    /* Schema */

    public static final Schema cardDetailSchema = new Schema("cardDetail");

    public static FieldDescriptor[] cardDetialDto = cardDetailDto("");


    public static FieldDescriptor[] cardDetailDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"cardId").description("단어카드의 고유번호"),
                fieldWithPath(prefix+"koreanWord").description("한국어 단어"),
                fieldWithPath(prefix+"foreignWord").description("외국어 단어"),
                fieldWithPath(prefix+"level").description("단어의 수준 (easy, normal, hard)"),
                fieldWithPath(prefix+"languageCode").description("언어코드 (ISO 639-1)"),

                fieldWithPath(prefix+"headword").description("표제어"),
                fieldWithPath(prefix+"homographNumber").description("동형어 번호"),
                fieldWithPath(prefix+"partsOfSpeech").description("품사"),
                fieldWithPath(prefix+"pronunciation").description("발음"),
                fieldWithPath(prefix+"relatedWords").description("관련어"),
                fieldWithPath(prefix+"inflection").description("활용"),
                fieldWithPath(prefix+"exampleUsage").description("용례"),
        };
    }
}
