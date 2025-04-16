package com.kukhar.uklon.model;

public enum EntityType {
    PASSENGER("passenger"),
    DRIVER("driver"),
    VEHICLE("vehicle"),
    TRIP("trip");

    private final String type;

    EntityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
