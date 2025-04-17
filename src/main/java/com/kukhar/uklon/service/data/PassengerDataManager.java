package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.repository.PassengerRepository;
import com.kukhar.uklon.service.parser.PassengerParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.kukhar.uklon.common.ApplicationConstants.ROW_COUNT;

@Slf4j
@Component
@Order(3)
@RequiredArgsConstructor
public class PassengerDataManager extends EntityDataManager<Passenger> {

    private final Random random;
    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> generateRandomEntities() {
        List<Passenger> passengers = new LinkedList<>();
        for (int i = 1; i < ROW_COUNT; i++) {
            passengers.add(getRandomData(i));
        }
        return passengers;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Passenger> passengers = new ArrayList<>();
        for (String line : lines) {
            try {
                passengers.add(PassengerParser.parsePassenger(line));
            } catch (Exception e) {
                log.warn("Failed to parse line: {} - Error: {}", line, e.getMessage());
            }
        }

        passengerRepository.saveAll(passengers);

    }

    @Override
    public String getEntityHeaders() {
        return Passenger.getHeaders();
    }

    public Passenger getRandomData(Integer id) {
        return new Passenger(
                id,
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                random.nextDouble(),
                OffsetDateTime.now()
        );
    }

}
