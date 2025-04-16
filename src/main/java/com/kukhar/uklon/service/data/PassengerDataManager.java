package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@Order(3)
@RequiredArgsConstructor
public class PassengerDataManager implements EntityDataManager<Passenger> {

    private final Faker faker;
    private final Random random;
    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> generateRandomEntities() {
        List<Passenger> passengers = new LinkedList<>();
        for (int i = 1; i < 300; i++) {
            passengers.add(getRandomData(i));
        }
        return passengers;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Passenger> passengers = new ArrayList<>();
        for (String line : lines) {
            try {
                passengers.add(parsePassenger(line));
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

    private Passenger parsePassenger(String line) {
        String[] fields = line.split(",");

        return new Passenger(
                Integer.valueOf(fields[0]),
                fields[1],
                fields[2],
                Double.parseDouble(fields[3]),
                OffsetDateTime.parse(fields[4])
        );
    }


}
