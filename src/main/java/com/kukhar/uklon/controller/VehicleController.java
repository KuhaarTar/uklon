package com.kukhar.uklon.controller;

import com.kukhar.uklon.common.ApiRoutes;
import com.kukhar.uklon.model.Vehicle;
import com.kukhar.uklon.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Vehicle.BASE)
@RequiredArgsConstructor
public class VehicleController implements ResourceController<Vehicle> {

    private final ResourceService<Vehicle> vehicleService;

    @Override
    @GetMapping(ApiRoutes.Vehicle.GET_ALL)
    public ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }
}
