package com.kmu.anki.backend.domain.usercard.docs;

import com.epages.restdocs.apispec.ConstrainedFields;
import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.domain.card.docs.CardDtoDocs;
import com.kmu.anki.backend.domain.card.docs.KoreanCardDtoDocs;
import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserCardDtoDocs {
    public static Schema userCardSchema = new Schema("userCard");
    public static Schema userCardsSchema = new Schema("userCards");

    public static FieldDescriptor[] userCard = userCardDto("");
    public static FieldDescriptor[] userCards = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            userCardDto(BaseDocs.basePageResponsePrefix)
    );

    public static FieldDescriptor[] userCardDto(String prefix){
        FieldDescriptor[] userCardDto =  new FieldDescriptor[]{
                fieldWithPath(prefix+"userCardId").type(JsonFieldType.NUMBER).optional().description("userCard의 고유번호"),
        };
        FieldDescriptor[] koreanCardAndStudyInfo = BaseDocs.combine(
                KoreanCardDtoDocs.koreanCardDtoDocs(prefix+"koreanCard."),
                StudyInfoDtoDocs.studyInfo(prefix+"studyInfo.")
        );
        return BaseDocs.combine(
            userCardDto,
            koreanCardAndStudyInfo
        );
    }
}
