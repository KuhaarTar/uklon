package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.model.Trip;
import com.kukhar.uklon.model.TripStatus;
import com.kukhar.uklon.repository.TripRepository;
import com.kukhar.uklon.service.parser.TripParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kukhar.uklon.common.ApplicationConstants.ROW_COUNT;

@Slf4j
@Order(4)
@Component
@RequiredArgsConstructor
public class TripDataManager extends EntityDataManager<Trip> {

    private final Random random;
    private final TripRepository tripRepository;

    @Override
    public String getEntityHeaders() {
        return Trip.getHeaders();
    }

    @Override
    public List<Trip> generateRandomEntities() {
        List<Trip> trips = new ArrayList<>();
        for (int i = 1; i < ROW_COUNT; i++) {
            var data = getRandomData(i);
            trips.add(data);
        }
        return trips;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Trip> trips = new ArrayList<>();

        for (String line : lines) {
            trips.add(TripParser.parseTrip(line));
        }

        tripRepository.saveAll(trips);
    }

    private TripStatus createRandomStatus() {
        return TripStatus.values()[random.nextInt(TripStatus.values().length)];
    }

    public Trip getRandomData(Integer id) {
        return new Trip(
                id,
                Driver.builder().id(id).build(),
                Passenger.builder().id(id).build(),
                super.createRandomLocation(),
                createRandomStatus()
        );
    }
}
