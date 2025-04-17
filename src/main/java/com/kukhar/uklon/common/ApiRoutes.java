package com.kukhar.uklon.common;

public class ApiRoutes {
    private static final String BASE_API = "/api";

    public static final class Trips {
        public static final String BASE = BASE_API + "/trips";
        public static final String GET_ALL = "";
        public static final String BY_ID = "/{id}";
    }

    public static final class Drivers {
        public static final String BASE = BASE_API + "/drivers";
        public static final String GET_ALL = "";
        public static final String BY_ID = "/{id}";
    }

    public static final class Vehicle {
        public static final String BASE = BASE_API + "/vehicles";
        public static final String GET_ALL = "";
        public static final String BY_ID = "/{id}";
    }

    public static final class Passenger {
        public static final String BASE = BASE_API + "/passengers";
        public static final String GET_ALL = "";
        public static final String BY_ID = "/{id}";
    }

    public static final class DataProvider {
        public static final String BASE = BASE_API + "/data";
        public static final String WRITE = "/write";
        public static final String READ = "/read";
    }
}

