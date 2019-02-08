package com.ack.movie.services.api.configuration;

public class OmdbApiConstants {

    public static final String OMDB_BASE_URL = "http://www.omdbapi.com/";
    public static final String API_KEY_TEXT = "apikey";
    public static final String API_KEY = "757296c7";
    public static final String TITLE_BASE = "t";

    private OmdbApiConstants() {
        throw new IllegalAccessError("Can not access GenericConstants");
    }
}
