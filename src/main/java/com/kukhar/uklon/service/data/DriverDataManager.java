package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.model.Location;
import com.kukhar.uklon.model.Vehicle;
import com.kukhar.uklon.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@Order(2)
@RequiredArgsConstructor
public class DriverDataManager implements EntityDataManager<Driver> {

    private final Faker faker;
    private final Random random;
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
                createRandomLocation()
        );
    }

    @Override
    public List<Driver> generateRandomEntities() {
        List<Driver> drivers = new ArrayList<>();
        for (int i = 1; i < 300; i++) {
            var data = getRandomData(i);
            drivers.add(data);
        }
        return drivers;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Driver> drivers = new ArrayList<>();

        for (String line : lines) {
            String[] tokens = line.split(",");
            Driver driver = new Driver();
            driver.setId(Integer.valueOf(tokens[0]));
            driver.setEmail(tokens[1]);
            driver.setPhoneNumber(tokens[2]);
            driver.setRating(Double.parseDouble(tokens[3]));
            driver.setCreatedAt(OffsetDateTime.parse(tokens[4]));
            driver.setVehicle(Vehicle.builder().id(Integer.valueOf(tokens[5])).build());

            Location location = new Location(
                    tokens[6],
                    tokens[7],
                    Double.parseDouble(tokens[8]),
                    Double.parseDouble(tokens[9])
            );
            driver.setLocation(location);

            drivers.add(driver);
        }

        driverRepository.saveAll(drivers);
    }

    private Location createRandomLocation() {
        return new Location(
                faker.address().latitude(),
                faker.address().longitude(),
                faker.number().randomDouble(2, 0, 500),
                faker.number().randomDouble(2, 1, 10)
        );
    }
}
