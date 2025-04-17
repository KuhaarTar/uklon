package com.kukhar.uklon.service.parser;

import com.kukhar.uklon.model.Driver;
import com.kukhar.uklon.model.Location;
import com.kukhar.uklon.model.Passenger;
import com.kukhar.uklon.model.Trip;
import com.kukhar.uklon.model.TripStatus;

public class TripParser extends EntityParser {

    public static Trip parseTrip(String line) {
        String[] tokens = parseField(line);

        return new Trip(
                Integer.valueOf((tokens[0])),
                Driver.builder().id(Integer.valueOf(tokens[1])).build(),
                Passenger.builder().id(Integer.valueOf(tokens[2])).build(),
                new Location(
                        Double.parseDouble(tokens[4]),
                        Double.parseDouble(tokens[5]),
                        Double.parseDouble(tokens[6]),
                        Double.parseDouble(tokens[7])
                ),
                TripStatus.valueOf(tokens[3])
        );
    }
}
