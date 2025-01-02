package com.kmu.anki.backend.file.access.impl;


import com.kmu.anki.backend.file.access.FileSystemAccessObject;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@Profile({"insert"})
@Component
public class LocalFileSystemAccessObject implements FileSystemAccessObject {
    private static String ROOT = "files/";

    @Override
    public void save(String path, Resource resource) {
        String localSavePath = Paths.get(ROOT + path).toString();
        File file = new File(localSavePath);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] content = resource.getContentAsByteArray(); // Assuming Resource has a getContent method returning byte[]
            outputStream.write(content);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void save(String path, MultipartFile file) {
        Path localSavePath = Paths.get(ROOT + path);
        try {
            file.transferTo(localSavePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resource load(String path) {
        Path localSavePath = Paths.get(ROOT + path);
        if(!Files.exists(localSavePath)){
            throw new NoSuchElementException();
        }
        try {
            return new UrlResource(localSavePath.toUri());
        }catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
    }
}
