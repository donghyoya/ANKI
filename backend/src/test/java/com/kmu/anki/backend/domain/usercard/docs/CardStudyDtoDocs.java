package com.kmu.anki.backend.domain.usercard.docs;

import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class CardStudyDtoDocs {
    public static Schema cardStudySchema = new Schema("cardStudyInfo");

    public static FieldDescriptor[] cardStudyInfo = cardStudyDto("");

    public static FieldDescriptor[] cardStudyDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"cardId").description("Card의 고유번호"),
                fieldWithPath(prefix+"nextStudyDate").description("다음 학습할 날짜"),
                fieldWithPath(prefix+"lapses").description("Again을 누른 횟수"),
                fieldWithPath(prefix+"lastReview").description("마지막으로 복습한 날짜"),
                fieldWithPath(prefix+"reps").description("총 복습횟수"),
                fieldWithPath(prefix+"scheduledDays").description("현재 복습 간격"),
                fieldWithPath(prefix+"stability").description("기억의 안정도"),
                fieldWithPath(prefix+"state").description("카드의 현재 상태")
        };
    }

}
