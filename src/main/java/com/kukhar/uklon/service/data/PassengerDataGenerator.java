package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.kukhar.uklon.utils.ApplicationConstants.ROW_COUNT;

@Slf4j
@Component
@RequiredArgsConstructor
public class PassengerDataGenerator implements EntityDataManager {

    private final Faker faker;
    private final PassengerRepository passengerRepository;

    @Override
    public void generateEntitiesToCsv(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(Passenger.getHeaders());
            writer.newLine();

            List<Passenger> passengers = new ArrayList<>();

            for (int i = 0; i < ROW_COUNT; i++) {
                Passenger passenger = getRandomPassenger();
                passengers.add(passenger);
                writer.write(passenger.toCsv());
                writer.newLine();
            }

            log.info("Successfully generated and saved " + ROW_COUNT + " passengers.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV file", e);
        }
    }

    @Override
    public void readEntitiesFromCsvToDb(File file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Skip the header line
            reader.readLine();

            String line;
            List<Passenger> passengers = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                Passenger passenger = parsePassenger(line);
                passengers.add(passenger);
            }

            passengerRepository.saveAll(passengers);
            log.info("Successfully loaded " + passengers.size() + " passengers into the database.");

        } catch (IOException e) {
            log.error("Failed to read the CSV file", e);
        }
    }

    private Passenger getRandomPassenger() {
        return new Passenger(
                UUID.randomUUID(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                faker.number().randomDouble(1, 1, 5),
                OffsetDateTime.now()
        );
    }

    private Passenger parsePassenger(String line) {
        String[] fields = line.split(",");

        return new Passenger(
                UUID.fromString(fields[0]),
                fields[1],
                fields[2],
                Double.parseDouble(fields[3]),
                OffsetDateTime.parse(fields[4])
        );
    }

}
