package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Vehicle;
import com.kukhar.uklon.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class VehicleDataManager implements EntityDataManager<Vehicle> {

    private final Faker faker;
    private final Random random;
    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> generateRandomEntities() {
        List<Vehicle> vehicles = new LinkedList<>();
        for (int i = 1; i < 300; i++) {
            vehicles.add(getRandomData(i));
        }
        return vehicles;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Vehicle> vehicles = new LinkedList<>();
        for (String line : lines) {
            try {
                vehicles.add(parseVehicle(line));
            } catch (Exception e) {
                System.out.println("Error parsing vehicle: " + line + " -> " + e.getMessage());
            }
        }

        vehicleRepository.saveAll(vehicles);
    }


    @Override
    public String getEntityHeaders() {
        return Vehicle.getHeaders();
    }

    public Vehicle getRandomData(Integer id) {
        return new Vehicle(id   ,
                faker.vehicle().model(),
                random.nextInt(),
                faker.color().name(),
                faker.ancient().titan() + " " + faker.number().digits(4) + "-" + faker.number().digits(2) + "-" + faker.number().digits(2)
        );
    }

    private Vehicle parseVehicle(String line) {
        String[] fields = line.split(",");
        log.info(Arrays.toString(fields));
        return new Vehicle(
                Integer.valueOf(fields[0]),
                fields[1],
                Integer.parseInt(fields[2]),
                fields[3],
                fields[4]
        );
    }

}
