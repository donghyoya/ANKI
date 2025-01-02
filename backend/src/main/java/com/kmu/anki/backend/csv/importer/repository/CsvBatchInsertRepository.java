package com.kmu.anki.backend.csv.importer.repository;

import org.springframework.stereotype.Component;

import java.util.List;

public interface CsvBatchInsertRepository {
    void batchInsert(List<String[]> rows);
    boolean isSupport(String type);
}
