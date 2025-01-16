package com.kmu.anki.backend.domain.study.history.controller.docs;

import com.epages.restdocs.apispec.Schema;
import com.kmu.anki.backend.global.BaseDocs;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserStudyHistoryDtoDocs {
    public static Schema userStudyHistorySchema = new Schema("userStudyHistory");
    public static Schema userStudyHistoriesSchema = new Schema("userStudyHistories");

    public static FieldDescriptor[] userStudyHistory = userStudyHistory("");
    public static FieldDescriptor[] userStudyHistories = BaseDocs.combine(
            BaseDocs.basePageResponse(),
            UserStudyHistoryDtoDocs.userStudyHistory(BaseDocs.basePageResponsePrefix)
    );

    public static FieldDescriptor[] userStudyHistory(String prefix){
        FieldDescriptor[] userStudyHistory =  new FieldDescriptor[]{
                fieldWithPath(prefix+"deckType").description(""),
                fieldWithPath(prefix+"deckName").description(""),
                fieldWithPath(prefix+"studyType").description("study를 했는지 review를 했는지"),
                fieldWithPath(prefix+"studyDate").description("최근 학습일자"),
        };
        return userStudyHistory;
    }

}
