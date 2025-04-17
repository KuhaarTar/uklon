package com.kukhar.uklon.controller;

import com.kukhar.uklon.model.CsvEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceController<E extends CsvEntity> {

    ResponseEntity<List<E>> findAll();
}
