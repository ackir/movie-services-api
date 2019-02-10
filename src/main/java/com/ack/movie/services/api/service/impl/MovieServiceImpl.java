package com.ack.movie.services.api.service.impl;

import com.ack.movie.services.api.configuration.CacheConfiguration;
import com.ack.movie.services.api.configuration.OmdbApiConstants;
import com.ack.movie.services.api.persistance.entity.Movie;
import com.ack.movie.services.api.persistance.repository.MovieRepository;
import com.ack.movie.services.api.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Cacheable(value = CacheConfiguration.MOVIE_CACHE, unless = "#result == null")
    public Movie getMovieById(String title) {
        log.info("MovieService is started for this movie : {}", title);

        Optional<Movie> existingMovie = movieRepository.findById(title);
        if (existingMovie.isPresent()) {
            log.info("Movie returned from database.");
            return existingMovie.get();
        } else {

            log.info("No movie found in database requested to OMDB API. ");
            Movie omdbResponseMovie = getMovieFromOMDBApi(title);
            if (Objects.nonNull(omdbResponseMovie.getErrorCode())
                    && !omdbResponseMovie.getErrorCode().equals(OmdbApiConstants.NOT_FOUND_ERROR_CODE)) {
                movieRepository.save(omdbResponseMovie);
            }
            return omdbResponseMovie;
        }
    }

    private Movie getMovieFromOMDBApi(String title) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Movie omdbApiMovieResponse = restTemplate.getForObject(constructMovieUri(title).toUriString(), Movie.class);
            if (Objects.requireNonNull(omdbApiMovieResponse).getResponse().equals(Boolean.FALSE)) {
                return Movie.builder()
                        .errorCode(OmdbApiConstants.NOT_FOUND_ERROR_CODE)
                        .errorMessages(OmdbApiConstants.NOT_FOUND_ERROR_MESSAGE)
                        .build();
            } else {
                return omdbApiMovieResponse;
            }

        } catch (HttpClientErrorException e) {
            final HttpStatus httpStatus = e.getStatusCode();

            if (Objects.equals(httpStatus, HttpStatus.NOT_FOUND)) {
                log.info("No movie found in OMBD API for given title. ");
                return Movie.builder()
                        .errorCode(OmdbApiConstants.NOT_FOUND_ERROR_CODE)
                        .errorMessages(OmdbApiConstants.NOT_FOUND_ERROR_MESSAGE)
                        .build();
            } else {
                log.error("HttpStatus : " + httpStatus + " returned!", e);
            }
        } catch (Exception e) {
            log.error("Unexpected error occurred while getting movie!", e);
        }
        return Movie.builder()
                .errorCode(OmdbApiConstants.NOT_FOUND_ERROR_CODE)
                .errorMessages(OmdbApiConstants.UNEXPECTED_ERROR_MESSAGE)
                .build();
    }

    private UriComponents constructMovieUri(String title) {
        return UriComponentsBuilder
                .fromHttpUrl(OmdbApiConstants.OMDB_BASE_URL)
                .queryParam(OmdbApiConstants.API_KEY_TEXT, OmdbApiConstants.API_KEY)
                .queryParam(OmdbApiConstants.TITLE_BASE, title)
                .build();
    }
}
