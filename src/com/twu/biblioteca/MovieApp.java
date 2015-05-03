package com.twu.biblioteca;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;

import java.util.List;

public class MovieApp extends Library<Movie>{
    public MovieApp() {
        commodities = Lists.newArrayList();

        commodities.add(buildMovie("Godzilla", "Gareth Edwards", Rating.EIGHT_SCORE, 2014));
        commodities.add(buildMovie("Hello", "kylefang", Rating.NINE_SCORE, 2014));
        commodities.add(buildMovie("World", "Zhigang", Rating.EIGHT_SCORE, 2014));
    }

    private Movie buildMovie(String name, String director, Rating rating, int year) {
        return Movie.builder()
                .withName(name)
                .withYear(year)
                .withDirector(director)
                .withRating(rating)
                .build();
    }

    public ImmutableList<Movie> showMovies() {
        return notCheckedoutCommodities();
    }

    public ImmutableList<Movie> showCheckedoutMovies() {
        return checkedoutCommodities();
    }

}
