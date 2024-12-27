package com.kmu.anki.backend.domain.usercard.controller;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.domain.card.docs.CardDocs;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserCardDocs {
    public static Schema studyCardFormSchema = new Schema("studyCardForm");
    public static Schema userCardSchema = new Schema("userCard");
    public static Schema userCardsSchema = new Schema("userCards");
    public static Schema cardStudySchema = new Schema("cardStudyInfo");

    private static FieldDescriptor[] _studyRequestForm = new FieldDescriptor[]{
            fieldWithPath("difficulty").description("언어의 난이도 (easy, normal, hard)"),
            fieldWithPath("languageCode").description("언어코드 (ISO 639-1)")
    };

    public static FieldDescriptor[] studyRequestForm(){
        return _studyRequestForm;
    }

    private static FieldDescriptor[] _studyCardForm = new FieldDescriptor[]{
            fieldWithPath("nextStudyDate").description("다음 학습할 날짜"),
            fieldWithPath("lapses").description("Again을 누른 횟수"),
            fieldWithPath("lastReview").description("마지막으로 복습한 날짜"),
            fieldWithPath("reps").description("총 복습횟수"),
            fieldWithPath("scheduledDays").description("현재 복습 간격"),
            fieldWithPath("stability").description("기억의 안정도"),
            fieldWithPath("state").description("카드의 현재 상태")
    };

    public static FieldDescriptor[] studyCardForm(){
        return _studyCardForm;
    }


    public static FieldDescriptor[] userDeckDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"id").description("userDeck의 고유번호"),
                fieldWithPath(prefix+"counts").description("이 덱에 포함된 카드 숫자"),
                fieldWithPath(prefix+"difficulty").description("언어의 난이도 (easy, normal, hard)"),
                fieldWithPath(prefix+"languageCode").description("언어코드 (ISO 639-1)")
        };
    }

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
                fieldWithPath(prefix+"state").description("카드의 현재 상태")
        };
        return BaseDocs.combine(CardDocs.cardDto(prefix), userCardDto);
    }

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
