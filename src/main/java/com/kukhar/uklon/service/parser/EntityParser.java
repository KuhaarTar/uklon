package com.kukhar.uklon.service.parser;

public abstract class EntityParser {

    protected static String[] parseField(String line) {
        return line.split(",");
    }
}
