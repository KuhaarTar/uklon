package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.CsvEntity;

import java.util.List;

public interface EntityDataManager<E extends CsvEntity> {
    List<E> generateRandomEntities();

    void readEntitiesFromLines(List<String> lines);

    String getEntityHeaders();
}
