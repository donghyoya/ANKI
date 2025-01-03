package com.kmu.anki.backend.csv.importer.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CsvBatchInsertRepositoryManager {
    private final List<CsvBatchInsertRepository> batchInsertRepositories;

    @Transactional
    public void batchInsert(String type, List<String[]> rows){
        for(CsvBatchInsertRepository batchInsertRepository : batchInsertRepositories){
            if(batchInsertRepository.isSupport(type)){
                batchInsertRepository.batchInsert(rows);
                return;
            }
        }
        throw new RuntimeException();
    }

}
