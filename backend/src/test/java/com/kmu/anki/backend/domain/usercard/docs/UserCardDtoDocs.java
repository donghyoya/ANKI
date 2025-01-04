package com.kmu.anki.backend.domain.usercard.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.domain.card.docs.CardDtoDocs;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserCardDtoDocs {
    public static Schema userCardSchema = new Schema("userCard");
    public static Schema userCardsSchema = new Schema("userCards");

    public static FieldDescriptor[] userCard = userCardDto("");
    public static FieldDescriptor[] userCards = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            UserCardDtoDocs.userCardDto(BaseDocs.basePageResponsePrefix)
    );

    public static FieldDescriptor[] userCardDto(String prefix){
        FieldDescriptor[] userCardDto =  new FieldDescriptor[]{
                fieldWithPath(prefix+"userCardId").description("userCard의 고유번호"),
                fieldWithPath(prefix+"score").description("해당 카드의 점수"),
                fieldWithPath(prefix+"nextStudyDate").description("다음 학습할 날짜"),
                fieldWithPath(prefix+"lapses").description("Again을 누른 횟수"),
                fieldWithPath(prefix+"lastReview").description("마지막으로 복습한 날짜"),
                fieldWithPath(prefix+"reps").description("총 복습횟수"),
                fieldWithPath(prefix+"scheduledDays").description("현재 복습 간격"),
                fieldWithPath(prefix+"stability").description("기억의 안정도"),
                fieldWithPath(prefix+"state").description("카드의 현재 상태"),
                fieldWithPath(prefix+"difficulty").description("카드의 난이도 (학습)")
        };
        return BaseDocs.combine(CardDtoDocs.cardFields(prefix), userCardDto);
    }
}
