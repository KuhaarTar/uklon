package com.kukhar.uklon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends CsvEntity implements IUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String phoneNumber;
    private double rating;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    @Embedded
    private Location location;

    @JsonIgnore
    public static String getHeaders() {
        return "id,email,phoneNumber,rating,createdAt,vehicleId," + Location.getHeaders();
    }

    @Override
    public String toCsv() {
        return id + "," +
                email + "," +
                phoneNumber + "," +
                rating + "," +
                createdAt + "," +
                (vehicle != null ? vehicle.getId() : "") + "," +
                location.toCsv();
    }
}

