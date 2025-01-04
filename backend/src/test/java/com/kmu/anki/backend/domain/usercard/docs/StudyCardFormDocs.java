package com.kmu.anki.backend.domain.usercard.docs;

import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class StudyCardFormDocs {

    public static Schema studyCardFormSchema = new Schema("studyCardForm");

    public static FieldDescriptor[] studyCardForm = new FieldDescriptor[]{
            fieldWithPath("nextStudyDate").description("다음 학습할 날짜"),
            fieldWithPath("lapses").description("Again을 누른 횟수"),
            fieldWithPath("lastReview").description("마지막으로 복습한 날짜"),
            fieldWithPath("reps").description("총 복습횟수"),
            fieldWithPath("scheduledDays").description("현재 복습 간격"),
            fieldWithPath("stability").description("기억의 안정도"),
            fieldWithPath("state").description("카드의 현재 상태")
    };

}
