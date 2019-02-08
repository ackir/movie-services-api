package com.ack.movie.services.api.persistance.repository;

import com.ack.movie.services.api.persistance.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
}
