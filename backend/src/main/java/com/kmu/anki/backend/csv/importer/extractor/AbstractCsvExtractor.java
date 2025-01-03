package com.kmu.anki.backend.csv.importer.extractor;

import com.kmu.anki.backend.csv.importer.dto.CsvExtractResult;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public abstract class AbstractCsvExtractor implements CsvExtractor{
    /**
     * CSV 데이터를 검증 및 데이터 추출하여 반환한다
     * @param resource
     * @return
     */
    @Override
    public CsvExtractResult extract(Resource resource){
        CsvParser parser = getCsvParser();
        CsvExtractResult ret = new CsvExtractResult();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))){
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

    /**
     * CSV의 특정 row가 정상적인 데이터인가?
     * @param row 검증할 CSV Row 데이터
     * @return CSV ROW 데이터의 검증 결과
     */
    protected abstract boolean isValid(String[] row);

    /**
     * 편의성 메서드. 자주 사용되는 csv parser를 반환한다
     * @return Csv Parser 반환
     */
    protected CsvParser getCsvParser(){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        return new CsvParser(settings);
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
