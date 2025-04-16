package com.kukhar.uklon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location extends CsvEntity {
    private String latitude;
    private String longitude;
    private double altitude;
    private double accuracy;

    @JsonIgnore
    public static String getHeaders() {
        return "latitude,longitude,altitude,accuracy";
    }

    public String toCsv() {
        return latitude + "," + longitude + "," + altitude + "," + accuracy;
    }
}

