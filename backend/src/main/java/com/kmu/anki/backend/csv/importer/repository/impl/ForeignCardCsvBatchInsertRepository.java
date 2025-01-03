package com.kmu.anki.backend.csv.importer.repository.impl;

import com.kmu.anki.backend.csv.importer.repository.CsvBatchInsertRepository;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ForeignCardCsvBatchInsertRepository implements CsvBatchInsertRepository {

    @Override
    public void batchInsert(List<String[]> rows) {
        List<ForeignCardRow> foreignCardRows = new ArrayList<>();
        for(int i=0;i<rows.size();i++){
            convert(i, rows.get(i), foreignCardRows);
        }

    }

    private void convert(int i, String[] row, List<ForeignCardRow> container){
        /**
         *  25 몽골어
         *  27 아랍어
         *  29 중국어
         *  31 베트남어
         *  33 태국어
         *  35 인도네시아어
         *  37 러시아어
         *  39 영어
         *  41 일본어
         *  43 프랑스어
         *  45 스페인어
         */
        Long koreanId = (long) i;
        LanguageCode[] values = LanguageCode.values();
        int wordNum = 25;
        for(LanguageCode code : values){
            container.add(
                    new ForeignCardRow(koreanId, code, row[wordNum], row[wordNum+1])
            );
            wordNum += 2;
        }
    }

    @Override
    public boolean isSupport(String type) {
        return "TYPE".equals(type);
    }

    @Getter
    static class ForeignCardRow{
        private Long koreanId;
        private LanguageCode code;
        private String foreignWord;
        private String foreignMeaning;

        public ForeignCardRow(Long koreanId, LanguageCode code, String foreignWord, String foreignMeaning) {
            this.koreanId = koreanId;
            this.code = code;
            this.foreignWord = foreignWord;
            this.foreignMeaning = foreignMeaning;
        }
    }
}
