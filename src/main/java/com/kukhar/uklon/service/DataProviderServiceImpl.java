package com.kukhar.uklon.service;

import com.kukhar.uklon.model.CsvEntity;
import com.kukhar.uklon.service.data.EntityDataManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.kukhar.uklon.common.ApplicationConstants.CSV_FILE_PATH;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataProviderServiceImpl implements DataProviderService {

    private final List<EntityDataManager<? extends CsvEntity>> dataGeneratorList;
    public static final Path path = Paths.get(CSV_FILE_PATH);

    @Override
    public void generateDataForEntities() {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = path.toFile();
        try (var writer = new BufferedWriter(new FileWriter(file))) {
            for (EntityDataManager<? extends CsvEntity> manager : dataGeneratorList) {
                writer.write("# ==== " + manager.getClass().getSimpleName() + " ====");
                writer.newLine();

                writer.write(manager.getEntityHeaders());
                writer.newLine();

                List<? extends CsvEntity> entities = manager.generateRandomEntities();
                for (CsvEntity entity : entities) {
                    writer.write(entity.toCsv());
                    writer.newLine();
                }

                writer.newLine();
            }
            log.info("CSV data successfully written to: {}", file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Failed to write CSV file", e);
        }
    }

    @Override
    public void readDataToDb() {
        File file = path.toFile();
        if (!file.exists()) {
            throw new RuntimeException("CSV file not found: " + file.getAbsolutePath());
        }

        try (var reader = Files.newBufferedReader(path)) {
            String line;
            EntityDataManager<? extends CsvEntity> currentManager = null;
            List<String> currentEntityLines = new java.util.ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("# ====")) {

                    if (currentManager != null && !currentEntityLines.isEmpty()) {
                        currentManager.readEntitiesFromLines(currentEntityLines);
                        currentEntityLines.clear();
                    }

                    final String currentManagerName = line.replaceAll("#\\s*=+\\s*|=+", "").trim();

                    currentManager = dataGeneratorList.stream()
                            .filter(m -> m.getClass().getSimpleName().equals(currentManagerName))
                            .findFirst()
                            .orElse(null);

                    if (currentManager == null) {
                        log.warn("No manager found for block: {}", currentManagerName);
                    }

                    reader.readLine();
                    continue;
                }

                if (currentManager != null && !line.isBlank()) {
                    currentEntityLines.add(line);
                }
            }

            if (currentManager != null && !currentEntityLines.isEmpty()) {
                currentManager.readEntitiesFromLines(currentEntityLines);
            }

            log.info("Successfully loaded all entities into the database.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load data from CSV", e);
        }
    }

}



