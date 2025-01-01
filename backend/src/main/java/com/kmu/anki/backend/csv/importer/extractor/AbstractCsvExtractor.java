package com.kmu.anki.backend.csv.importer.extractor;

import com.kmu.anki.backend.csv.importer.dto.CsvExtractResult;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
public abstract class AbstractCsvExtractor {
    /**
     * CSV 데이터 TYPE을 이 추출기가 추출할 수 있는가?
     * @param type CSV 데이터의 타입 (사전에 합의할 것. 소문자 우선)
     * @return
     */
    public abstract boolean isSupport(String type);

    /**
     * 추출기에 의해 정상 추출된 데이터에 대해서
     * @param row 정상 추출된 CSV Row 데이터
     * @return
     */
    public abstract boolean isValid(String[] row);

    public CsvParser getCsvParser(){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        return new CsvParser(settings);
    }

    public CsvExtractResult extract(Resource resource){
        CsvParser parser = getCsvParser();
        CsvExtractResult ret = new CsvExtractResult();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            for(String[] row : parser.iterate(reader)){
                if(isValid(row)){
                    ret.addValidRecords(row);
                }else {
                    ret.addInValidRecords(row);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    protected boolean isBlank(String record){
        return record != null && !record.isBlank();
    }

    protected boolean isInteger(String record){
        try {
            int number = Integer.parseInt(record);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    protected boolean isLong(String record){
        try {
            Long number = Long.parseLong(record);
        }catch (NumberFormatException e){
            return false;
        }
        return true;

    }

    protected boolean isPositiveInteger(String record){
        try{
            int number = Integer.parseInt(record);
            if(number<0){
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
