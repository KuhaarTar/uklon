package com.kukhar.uklon.service;

import com.kukhar.uklon.model.Vehicle;
import com.kukhar.uklon.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService implements ResourceService<Vehicle> {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}
