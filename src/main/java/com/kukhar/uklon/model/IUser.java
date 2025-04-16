package com.kukhar.uklon.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IUser {
    UUID getId();

    String getEmail();

    String getPhoneNumber();

    double getRating();

    OffsetDateTime getCreatedAt();
}
