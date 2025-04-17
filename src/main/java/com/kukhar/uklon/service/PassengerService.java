package com.kukhar.uklon.service;

import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService implements ResourceService<Passenger> {

    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }
}
