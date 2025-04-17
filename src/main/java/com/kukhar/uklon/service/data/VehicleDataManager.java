package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Vehicle;
import com.kukhar.uklon.repository.VehicleRepository;
import com.kukhar.uklon.service.parser.VehicleParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.kukhar.uklon.common.ApplicationConstants.ROW_COUNT;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class VehicleDataManager extends EntityDataManager<Vehicle> {

    private final Random random;
    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> generateRandomEntities() {
        List<Vehicle> vehicles = new LinkedList<>();
        for (int i = 1; i < ROW_COUNT; i++) {
            vehicles.add(getRandomData(i));
        }
        return vehicles;
    }

    @Override
    public void readEntitiesFromLines(List<String> lines) {
        List<Vehicle> vehicles = new LinkedList<>();
        for (String line : lines) {
            try {
                vehicles.add(VehicleParser.parseVehicle(line));
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
        return new Vehicle(id,
                faker.vehicle().model(),
                random.nextInt(),
                faker.color().name(),
                faker.ancient().titan() + " " + faker.number().digits(4) + "-" + faker.number().digits(2) + "-" + faker.number().digits(2)
        );
    }

}
