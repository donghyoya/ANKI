package com.kmu.anki.backend.file.access.impl;

import com.kmu.anki.backend.file.access.FileSystemAccessObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class LocalFileSystemAccessObjectTest {
    private final FileSystemAccessObject fileSAO = new LocalFileSystemAccessObject();

    @Test
    void load() {
        fileSAO.load("insert_data.csv");
    }
}