package com.kukhar.uklon.service;

import com.kukhar.uklon.model.Trip;
import com.kukhar.uklon.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService implements ResourceService<Trip> {

    private final TripRepository tripRepository;

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }
}
