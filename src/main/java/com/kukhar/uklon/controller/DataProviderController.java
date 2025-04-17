package com.kukhar.uklon.controller;

import com.kukhar.uklon.common.ApiRoutes;
import com.kukhar.uklon.service.DataProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.DataProvider.BASE)
public class DataProviderController {

    private final DataProviderService dataGenerator;

    @PostMapping(ApiRoutes.DataProvider.WRITE)
    public ResponseEntity<Void> generateDataAndWriteToDb() {
        dataGenerator.generateDataForEntities();
        return ResponseEntity.ok().build();
    }

    @PostMapping(ApiRoutes.DataProvider.READ)
    public ResponseEntity<Void> readDataToDb() {
        dataGenerator.readDataToDb();
        return ResponseEntity.ok().build();
    }
}
