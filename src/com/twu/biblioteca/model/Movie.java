package com.twu.biblioteca.model;

import com.google.common.base.Function;
import com.google.common.base.Objects;

import static com.google.common.base.Objects.equal;

public class Movie implements Commodity {
    private String name;
    private int year;
    private String director;
    private Rating rating = Rating.UNRATED;
    private boolean isCheckedout = false;

    public static String[] tableHeader() {
        return new String[]{"name", "year", "director", "rating"};
    }

    public boolean isCheckedout() {
        return isCheckedout;
    }

    public void setCheckedout(boolean checkedout) {
        isCheckedout = checkedout;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("Name", name)
                .add("Year", year)
                .add("Director", director)
                .add("Rating", rating)
                .add("Is Checkedout", isCheckedout)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return equal(name, movie.name) &&
               equal(year, movie.year) &&
               equal(director, movie.director) &&
               equal(rating, movie.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, year, director, rating);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Movie toMovie(Object[] model) {
        return Movie.builder().withName((String) model[0])
                .withYear((Integer) model[1])
                .withDirector((String) model[2])
                .withRating((Rating) model[3])
                .build();
    }

    public static Function<Movie, Object[]> toObjectArray() {
        return new Function<Movie, Object[]>() {
            @Override
            public Object[] apply(Movie movie) {
                return new Object[]{movie.getName(),
                        movie.getYear(),
                        movie.getDirector(),
                        movie.getRating()};
            }
        };

    }

    public static class Builder {
        private String name;
        private int year;
        private String director;
        private Rating rating = Rating.UNRATED;
        private boolean isCheckedout = false;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withYear(int year) {
            this.year = year;
            return this;
        }

        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder withRating(Rating rating) {
            this.rating = rating;
            return this;
        }

        public Builder withCheckedout(boolean checkedout) {
            isCheckedout = checkedout;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.name = name;
            movie.year = year;
            movie.director = director;
            movie.rating = rating;
            movie.isCheckedout = isCheckedout;
            return movie;
        }
    }
}
