package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class DeckDtoDocs {
    public static final Schema deckSceham = new Schema("deck");
    public static final Schema decksSceham = new Schema("decks");

    public static final FieldDescriptor[] deck = deckDto("");

    public static final FieldDescriptor[] decks = BaseDocs.combine(
            BaseDocs.baseListResponse(),
            deckDto(BaseDocs.basePageResponsePrefix)
    );

    public static FieldDescriptor[] deckDto(String prefix){
        return new FieldDescriptor[]{
                fieldWithPath(prefix+"category").type(JsonFieldType.STRING).description("카드 분류"),
                fieldWithPath(prefix+"cardCounts").type(JsonFieldType.NUMBER).description("덱에 포함된 카드 개수"),
                fieldWithPath(prefix+"newCounts")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("아예 본 적 없는 카드(State가 New)의 수"),
                fieldWithPath(prefix+"learningCounts")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("학습을 했다가 due가 지난 카드(State가 Review고 due가 지남)의 수"),
                fieldWithPath(prefix+"overdueCounts")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("최근에 입력한 Rating이 Again인 카드(State가 Learning 또는 Relearning)의 수"),
                fieldWithPath(prefix+"maturityCounts")
                        .type(JsonFieldType.NUMBER).optional()
                        .description("사용자가 기억하고 있다고 추정되는 카드(State가 Review)의 수")
        };
    }

}
