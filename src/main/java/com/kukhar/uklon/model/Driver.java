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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver implements IUser {

    @Id
    @GeneratedValue
    private UUID id;

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
        return "id,email,phoneNumber,rating,createdAt,location," + Location.getHeaders();
    }

    public String toCsv() {
        return email + "," + phoneNumber + "," + rating + "," + vehicle.toCsv() + "," + location.toCsv();
    }
}

