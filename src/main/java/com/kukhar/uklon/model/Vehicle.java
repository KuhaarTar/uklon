package com.kukhar.uklon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle extends CsvEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String model;
    private int capacity;
    private String color;
    private String registrationPlates;

    @JsonIgnore
    public static String getHeaders() {
        return "id,model,capacity,color,registrationPlates";
    }

    @Override
    public String toCsv() {
        return id + "," + model + "," + capacity + "," + color + "," + registrationPlates;
    }

}

