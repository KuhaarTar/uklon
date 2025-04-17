package com.kukhar.uklon.controller;

import com.kukhar.uklon.common.ApiRoutes;
import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Passenger.BASE)
@RequiredArgsConstructor
public class PassengerController implements ResourceController<Passenger> {

    private final ResourceService<Passenger> passengerService;

    @Override
    @GetMapping(ApiRoutes.Passenger.GET_ALL)
    public ResponseEntity<List<Passenger>> findAll() {
        return ResponseEntity.ok(passengerService.findAll());
    }
}
