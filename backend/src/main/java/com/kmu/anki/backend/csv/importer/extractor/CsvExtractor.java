package com.kmu.anki.backend.csv.importer.extractor;

import com.kmu.anki.backend.csv.importer.dto.CsvExtractResult;
import org.springframework.core.io.Resource;

public interface CsvExtractor {
    /**
     * CSV 데이터 TYPE을 이 추출기가 추출할 수 있는가?
     * @param type CSV 데이터의 타입 (사전에 합의할 것. 소문자 우선)
     * @return
     */
    public abstract boolean isSupport(String type);

    /**
     * CSV 데이터를 검증 및 데이터 추출하여 반환한다
     * @param resource
     * @return
     */
    public CsvExtractResult extract(Resource resource);
}
