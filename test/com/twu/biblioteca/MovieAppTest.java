package com.twu.biblioteca;

import com.google.common.base.Optional;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class MovieAppTest {

    private MovieApp movieApp;

    @Before
    public void setUp() throws Exception {
        movieApp = new MovieApp();
    }

    @Test
    public void should_list_movies() throws Exception {
        List<Movie> movies = movieApp.showMovies();

        assertThat(movies.size(), greaterThan(0));
    }

    @Test
    public void should_check_out_a_movie() throws Exception {
        Optional<Movie> movie = movieApp.checkout(buildMovie("Godzilla", "Gareth Edwards", Rating.EIGHT_SCORE, 2014));

        assertThat(movie.isPresent(), is(true));
    }

    @Test
    public void should_return_a_movie() throws Exception {
        Movie movie = buildMovie("Godzilla", "Gareth Edwards", Rating.EIGHT_SCORE, 2014);
        movieApp.checkout(movie);

        assertThat(movieApp.returned(movie), is(true));
    }

    @Test
    public void should_not_return_a_not_checked_out_movie() throws Exception {
        Movie movie = buildMovie("Hello", "codeeker", Rating.NINE_SCORE, 2014);
        movieApp.checkout(movie);
        Movie notCheckedoutMovie = buildMovie("Godzilla", "Gareth Edwards",
                Rating.EIGHT_SCORE, 2014);

        assertThat(movieApp.returned(notCheckedoutMovie), is(false));
    }

    private Movie buildMovie(String name, String director, Rating score, int year) {
        return Movie.builder()
                .withName(name)
                .withDirector(director)
                .withRating(score)
                .withYear(year)
                .build();
    }
}
