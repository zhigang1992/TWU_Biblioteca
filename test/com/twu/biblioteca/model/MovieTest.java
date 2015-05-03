package com.twu.biblioteca.model;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MovieTest {
    @Test
    public void should_has_name_year_director_and_rating_for_a_movie() throws Exception {
        Movie movie = buildMovie();

        assertThat(movie.getName(), is("Godzilla"));
        assertThat(movie.getYear(), is(2014));
        assertThat(movie.getDirector(), is("Gareth Edwards"));
        assertThat(movie.getRating(), is(Rating.EIGHT_SCORE));
    }

    private Movie buildMovie() {
        return Movie.builder()
                .withName("Godzilla")
                .withYear(2014)
                .withDirector("Gareth Edwards")
                .withRating(Rating.EIGHT_SCORE)
                .build();
    }

    @Test
    public void should_default_is_not_checked_out_for_a_movie() throws Exception {
        Movie movie = buildMovie();

        assertThat(movie.isCheckedout(), is(false));
    }
}
