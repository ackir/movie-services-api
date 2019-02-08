package com.ack.movie.services.api.persistance.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @JsonProperty("Title")
    private String title;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    @JsonProperty("MetaScore")
    private String metaScore;
    @JsonProperty("BoxOffice")
    private String boxOffice;
    @JsonProperty("WebSite")
    private String website;
    @JsonProperty("Ratings")
    private List<Rating> ratings;
    @JsonProperty("RunTime")
    private String runtime;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Production")
    private String production;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Response")
    private String response;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("DVD")
    private String dVD;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Writer")
    private String writer;
}