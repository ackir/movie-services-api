package com.ack.movie.services.api.controller;

import com.ack.movie.services.api.persistance.entity.Movie;
import com.ack.movie.services.api.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public Movie getMovie(@RequestParam String title) {
        return movieService.getMovieById(title);
    }
}
