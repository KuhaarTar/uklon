package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.model.Location;
import com.kukhar.uklon.model.Vehicle;
import com.kukhar.uklon.repository.DriverRepository;
import com.kukhar.uklon.service.parser.DriverParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.kukhar.uklon.common.ApplicationConstants.ROW_COUNT;

@Slf4j
@Component
@Order(2)
@RequiredArgsConstructor
public class DriverDataManager extends EntityDataManager<Driver> {

    private final Faker faker;
    private final DriverRepository driverRepository;

    @Override
    public String getEntityHeaders() {
        return Driver.getHeaders();
    }

    public Driver getRandomData(Integer id) {
        return new Driver(
                id,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                faker.number().randomDouble(1, 1, 5),
                OffsetDateTime.now(),
                Vehicle.builder().id(id).build(),
                super.createRandomLocation()
        );
    }

    @Override
    public List<Driver> generateRandomEntities() {
        List<Driver> drivers = new ArrayList<>();
        for (int i = 1; i < ROW_COUNT; i++) {
            var data = getRandomData(i);
            drivers.add(data);
        }
        return drivers;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Driver> drivers = new ArrayList<>();

        for (String line : lines) {
            drivers.add(DriverParser.parseDriver(line));
        }

        driverRepository.saveAll(drivers);
    }
}
