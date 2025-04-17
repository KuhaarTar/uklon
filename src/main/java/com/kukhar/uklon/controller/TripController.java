package com.kukhar.uklon.controller;

import com.kukhar.uklon.common.ApiRoutes;
import com.kukhar.uklon.model.Trip;
import com.kukhar.uklon.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Trips.BASE)
@RequiredArgsConstructor
public class TripController implements ResourceController<Trip> {

    private final ResourceService<Trip> tripService;

    @Override
    @GetMapping(ApiRoutes.Trips.GET_ALL)
    public ResponseEntity<List<Trip>> findAll() {
        return ResponseEntity.ok(tripService.findAll());
    }
}
