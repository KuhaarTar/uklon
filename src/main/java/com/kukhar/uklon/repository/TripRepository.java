package com.kukhar.uklon.repository;

import com.kukhar.uklon.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
