package com.kukhar.uklon.controller;

import com.kukhar.uklon.common.ApiRoutes;
import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Drivers.BASE)
@RequiredArgsConstructor
public class DriverController implements ResourceController<Driver> {

    private final ResourceService<Driver> driverService;

    @Override
    @GetMapping(ApiRoutes.Drivers.GET_ALL)
    public ResponseEntity<List<Driver>> findAll() {
        return ResponseEntity.ok(driverService.findAll());
    }
}
