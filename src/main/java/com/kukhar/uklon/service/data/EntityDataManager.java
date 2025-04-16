package com.kukhar.uklon.service.data;

import java.io.File;

public interface EntityDataManager {
    void generateEntitiesToCsv(File file);

    void readEntitiesFromCsvToDb(File file);
}
