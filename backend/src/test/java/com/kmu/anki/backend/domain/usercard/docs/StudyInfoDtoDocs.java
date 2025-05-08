package com.kmu.anki.backend.domain.usercard.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.domain.card.docs.CardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.KoreanCardDtoDocs;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class StudyInfoDtoDocs {
    public static Schema studyInfoSchema = new Schema("studyInfo");
    public static Schema studyInfosSchema = new Schema("studyInfos");

    public static FieldDescriptor[] studyInfo = studyInfo("");
    public static FieldDescriptor[] studyInfos = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            studyInfo(BaseDocs.basePageResponsePrefix)
    );

    public static FieldDescriptor[] studyInfo(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"due")
                        .type(JsonFieldType.STRING).optional()
                        .description("다음 학습할 날짜"),
                fieldWithPath(prefix+"lapses")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("Again을 누른 횟수"),
                fieldWithPath(prefix+"lastReview")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("마지막으로 복습한 날짜"),
                fieldWithPath(prefix+"reps")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("총 복습횟수"),
                fieldWithPath(prefix+"scheduledDays")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("현재 복습 간격"),
                fieldWithPath(prefix+"stability")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("기억의 안정도"),
                fieldWithPath(prefix+"state")
                        .type(JsonFieldType.STRING).optional()
                        .description("카드의 현재 상태"),
                fieldWithPath(prefix+"difficulty")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("difficulty")

        };
    }

}
