package com.kukhar.uklon.controller;

import com.kukhar.uklon.service.DataProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/data")
public class DataProviderController {

    private final DataProviderService dataGenerator;

    @PostMapping("/write")
    public ResponseEntity<Void> generateDataAndWriteToDb() {
        dataGenerator.generateDataForEntities();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/read")
    public ResponseEntity<Void> readDataToDb() {
        dataGenerator.readDataToDb();
        return ResponseEntity.ok().build();
    }
}
