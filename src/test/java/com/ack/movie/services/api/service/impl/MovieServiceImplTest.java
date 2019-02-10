package com.ack.movie.services.api.service.impl;

import com.ack.movie.services.api.configuration.OmdbApiConstants;
import com.ack.movie.services.api.persistance.entity.Movie;
import com.ack.movie.services.api.persistance.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MovieServiceImplTest {

    @Mock
    private MovieRepository mockMovieRepository;

    private MovieServiceImpl movieService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(mockMovieRepository);
    }

    @Test
    public void whenOmdbApiReturnResponseShouldResponseHasTrueResponseField() {

        //given
        Optional<Movie> movieReturnedTrue = buildMovie();
        String title = "title";

        //when
        when(mockMovieRepository.findById(any())).thenReturn(movieReturnedTrue);
        Movie movieReturnedTrueResponse = movieReturnedTrue.orElse(Movie.builder().build());

        //then
        Movie responseMovie = movieService.getMovieById(title);

        assertEquals(responseMovie.getTitle(), movieReturnedTrueResponse.getTitle());
        assertTrue(responseMovie.getResponse());
        assertNotNull(responseMovie.getTitle());
    }

    @Test
    public void whenOmdbApiNotFoundMovieShouldReturnErrorCodeAndDescription() {

        //given
        String notFoundTitle = "34234234234234";

        //when
        when(mockMovieRepository.findById(any())).thenReturn(Optional.empty());

        //then
        Movie responseMovie = movieService.getMovieById(notFoundTitle);

        assertEquals(responseMovie.getErrorMessages(), OmdbApiConstants.NOT_FOUND_ERROR_MESSAGE);
        assertEquals(responseMovie.getErrorCode().toString(), OmdbApiConstants.NOT_FOUND_ERROR_CODE.toString());

    }

    private Optional<Movie> buildMovie() {
        return Optional.ofNullable(Movie.builder()
                .title("title")
                .response(Boolean.TRUE)
                .build());
    }
}