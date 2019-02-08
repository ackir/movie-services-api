package com.ack.movie.services.api.service;

import com.ack.movie.services.api.persistance.entity.Movie;

public interface MovieService {

    Movie getMovieById(String title);
}
