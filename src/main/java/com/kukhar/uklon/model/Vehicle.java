package com.kukhar.uklon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue
    private UUID id;

    private String model;
    private int capacity;
    private String color;
    private String registrationPlates;

    @JsonIgnore
    private static String getHeaders() {
        return "id,model,capacity,color,registrationPlates";
    }

    public String toCsv() {
        return model + "," + capacity + "," + color + "," + registrationPlates;
    }
}

