package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.model.Location;
import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.model.Trip;
import com.kukhar.uklon.model.TripStatus;
import com.kukhar.uklon.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Order(4)
@Component
@RequiredArgsConstructor
public class TripDataManager implements EntityDataManager<Trip> {

    private final Faker faker;
    private final Random random;
    private final TripRepository tripRepository;

    @Override
    public String getEntityHeaders() {
        return Trip.getHeaders();
    }

    public Trip getRandomData(Integer id) {
        return new Trip(
                id,
                Driver.builder().id(id).build(),
                Passenger.builder().id(id).build(),
                createRandomLocation(),
                createRandomStatus()
        );
    }

    @Override
    public List<Trip> generateRandomEntities() {
        List<Trip> trips = new ArrayList<>();
        for (int i = 1; i < 300; i++) {
            var data = getRandomData(i);
            log.info("Generated trip: {}", data);
            trips.add(data);
        }
        return trips;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Trip> trips = new ArrayList<>();

        for (String line : lines) {
            String[] tokens = line.split(",");
            Trip trip = new Trip();
            trip.setId(Integer.valueOf((tokens[0])));
            trip.setDriver(Driver.builder().id(Integer.valueOf(tokens[1])).build());

            trip.setPassenger(Passenger.builder().id(Integer.valueOf(tokens[2])).build());

            trip.setStatus(TripStatus.valueOf(tokens[3]));
            Location destination = new Location(
                    tokens[4],
                    tokens[5],
                    Double.parseDouble(tokens[6]),
                    Double.parseDouble(tokens[7])
            );
            trip.setDestination(destination);

            trips.add(trip);
        }

        tripRepository.saveAll(trips);
    }

    private Location createRandomLocation() {
        return new Location(
                faker.address().latitude(),
                faker.address().longitude(),
                faker.number().randomDouble(2, 0, 500),
                faker.number().randomDouble(2, 0, 10)
        );
    }

    private TripStatus createRandomStatus() {
        return TripStatus.values()[random.nextInt(TripStatus.values().length)];
    }
}
