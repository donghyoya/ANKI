package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;

public class ForeignCardSearchResultDocs {
    public static final Schema foreignCardSearchResultSchema = new Schema("foreignCardSearchResult");
    public static final Schema foreignCardSearchResultsSchema = new Schema("foreignCardSearchResults");

    public static FieldDescriptor[] foreignCardSearchResults = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            foreignCardSearchResult(BaseDocs.basePageResponsePrefix)
    );


    public static FieldDescriptor[] foreignCardSearchResult(String prefix){
        return BaseDocs.combine(
                KoreanCardDtoDocs.koreanCardDtoDocs(prefix+"koreanCard."),
                CardDetailDtoDocs.cardMeaningWithForeign(prefix+"foreignCard.")
        );
    }

}
