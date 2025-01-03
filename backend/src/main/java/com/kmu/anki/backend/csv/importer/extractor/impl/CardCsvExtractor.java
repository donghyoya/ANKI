package com.kmu.anki.backend.csv.importer.extractor.impl;

import com.kmu.anki.backend.csv.importer.extractor.AbstractCsvExtractor;
import org.springframework.stereotype.Component;

@Component
public class CardCsvExtractor extends AbstractCsvExtractor {
    private static String TYPE = "card";
    @Override
    protected boolean isValid(String[] row) {
        return true;
    }

    @Override
    public boolean isSupport(String type) {
        return TYPE.equals(type);
    }
}
