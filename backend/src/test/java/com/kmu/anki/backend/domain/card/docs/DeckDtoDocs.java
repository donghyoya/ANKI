package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;

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
                fieldWithPath(prefix+"category").description("카드 분류"),
                fieldWithPath(prefix+"cardCounts").description("덱에 포함된 카드 개수"),
                fieldWithPath(prefix+"overdueRate").description("due가 지난 상태인 카드 비율"),
                fieldWithPath(prefix+"maturitiyRate").description("state가 review인 카드 비율:"),
                fieldWithPath(prefix+"overdueCounts").description("due가 지난 상태인 카드 개수"),
                fieldWithPath(prefix+"maturitiyCounts").description("state가 review인 카드 개수")
        };
    }

}
