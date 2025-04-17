package com.kukhar.uklon.service.parser;

import com.kukhar.uklon.model.Passenger;

import java.time.OffsetDateTime;

public class PassengerParser extends EntityParser {

    public static Passenger parsePassenger(String line) {
        String[] fields = parseField(line);

        return new Passenger(
                Integer.valueOf(fields[0]),
                fields[1],
                fields[2],
                Double.parseDouble(fields[3]),
                OffsetDateTime.parse(fields[4])
        );
    }
}
