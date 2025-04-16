package com.kukhar.uklon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends CsvEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;

    @Embedded
    private Location destination;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    @JsonIgnore
    public static String getHeaders() {
        return "id,driver,passenger,status," + Location.getHeaders();
    }

    @Override
    public String toCsv() {
        return id + "," +
                (driver != null ? driver.getId() : "") + "," +
                (passenger != null ? passenger.getId() : "") + "," +
                status.name() + "," +
                destination.toCsv();
    }
}

