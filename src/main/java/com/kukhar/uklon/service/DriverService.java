package com.kukhar.uklon.service;

import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService implements ResourceService<Driver> {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }
}
