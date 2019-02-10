package com.ack.movie.services.api.controller;

import com.ack.movie.services.api.persistance.entity.Movie;
import com.ack.movie.services.api.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private MockMvc mockMvc;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        movieController = new MovieController(movieService);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void getMovieByTitle() throws Exception {

        //given
        final Movie movieResponse = buildMovieForController();
        final String requestTitle = "title";

        //when
        doReturn(movieResponse).when(movieService).getMovieById(requestTitle);

        //then
        mockMvc.perform(get("/movie").param(requestTitle, requestTitle))
                .andExpect(status().isOk());

        verify(movieService, times(1)).getMovieById(requestTitle);
    }

    private Movie buildMovieForController() {
        return Movie.builder()
                .response(Boolean.TRUE)
                .build();
    }
}