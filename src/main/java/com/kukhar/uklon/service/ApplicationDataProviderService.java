package com.kukhar.uklon.service;

import com.kukhar.uklon.service.data.EntityDataManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.kukhar.uklon.utils.ApplicationConstants.CSV_FILE_PATH;

@Component
@RequiredArgsConstructor
public class ApplicationDataProviderService {

    private final List<EntityDataManager> dataGeneratorList;
    private static final Path path = Paths.get(CSV_FILE_PATH);

    public void generateDataForEntities() {
        try {
            Files.deleteIfExists(path);
            File file = path.toFile();
            provideDataToFile(file);
        } catch (IOException e) {
            throw new RuntimeException("Error while handling the file: " + e.getMessage(), e);
        }
    }

    public void readDataToDb() {
        File file = path.toFile();
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        }
        provideDataToDb(file);
    }

    public void provideDataToFile(File file) {
        for (EntityDataManager generator : dataGeneratorList) {
            generator.generateEntitiesToCsv(file);
        }
    }

    public void provideDataToDb(File file) {
        for (EntityDataManager generator : dataGeneratorList) {
            generator.readEntitiesFromCsvToDb(file);
        }
    }
}
