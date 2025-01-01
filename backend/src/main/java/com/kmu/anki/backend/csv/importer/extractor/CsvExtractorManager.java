package com.kmu.anki.backend.csv.importer.extractor;

import com.kmu.anki.backend.csv.importer.dto.CsvExtractResult;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CsvExtractorManager{
    private final List<AbstractCsvExtractor> csvExtractors;

    public Optional<CsvExtractResult> extract(String type, Resource resource) {
        for(AbstractCsvExtractor csvExtractor : csvExtractors){
            if(csvExtractor.isSupport(type)){
                return Optional.of(csvExtractor.extract(resource));
            }
        }
        return Optional.empty();
    }
}
