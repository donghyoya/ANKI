package com.kmu.anki.backend.csv.importer.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CsvExtractResult {
    private List<String[]> validRecords;
    private List<String[]> inValidRecords;

    public CsvExtractResult() {
        this.validRecords = new ArrayList<>();
        this.inValidRecords = new ArrayList<>();
    }

    public void addValidRecords(String[] record){
        validRecords.add(record);
    }

    public void addInValidRecords(String[] record){
        inValidRecords.add(record);
    }

}
