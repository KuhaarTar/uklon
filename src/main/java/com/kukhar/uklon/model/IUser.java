package com.kukhar.uklon.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IUser {
    Integer getId();

    String getEmail();

    String getPhoneNumber();

    double getRating();

    OffsetDateTime getCreatedAt();
}
