package com.kukhar.uklon.service.parser;

import com.kukhar.uklon.model.Vehicle;

public class VehicleParser extends EntityParser {

    public static Vehicle parseVehicle(String line) {
        String[] fields = parseField(line);

        return new Vehicle(
                Integer.valueOf(fields[0]),
                fields[1],
                Integer.parseInt(fields[2]),
                fields[3],
                fields[4]
        );
    }
}
