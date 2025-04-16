package com.kukhar.uklon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends CsvEntity implements IUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String phoneNumber;
    private double rating;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @JsonIgnore
    public static String getHeaders() {
        return "id,email,phoneNumber,rating,createdAt";
    }

    public String toCsv() {
        return id + "," +
                email + "," +
                phoneNumber + "," +
                rating + "," +
                createdAt;
    }
}

