package com.kukhar.uklon.service.parser;

import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.model.Location;
import com.kukhar.uklon.model.Vehicle;

import java.time.OffsetDateTime;

public class DriverParser extends EntityParser {

    public static Driver parseDriver(String line) {
        String[] tokens = parseField(line);

        return new Driver(
                Integer.parseInt(tokens[0]),
                tokens[1],
                tokens[2],
                Double.parseDouble(tokens[3]),
                OffsetDateTime.parse(tokens[4]),
                Vehicle.builder().id(Integer.parseInt(tokens[5])).build(),
                new Location(
                        Double.parseDouble(tokens[6]),
                        Double.parseDouble(tokens[7]),
                        Double.parseDouble(tokens[8]),
                        Double.parseDouble(tokens[9])
                )
        );
    }
}
