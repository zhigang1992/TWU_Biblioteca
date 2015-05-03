package com.twu.biblioteca.controller;

import com.google.common.base.Function;
import com.twu.biblioteca.MovieApp;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.view.MainFrame;

import static com.twu.biblioteca.model.Movie.tableHeader;
import static com.twu.biblioteca.model.Movie.toObjectArray;

public class MovieController extends MainController<Movie>{

    protected final MovieApp app = new MovieApp();
    public MovieController(MainFrame mainFrame) {
        super(mainFrame);
        TABLE_HEADER = tableHeader();
    }



    @Override
    protected Function<Movie, Object[]> toArray() {
        return toObjectArray();
    }
}
