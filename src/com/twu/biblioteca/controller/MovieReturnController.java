package com.twu.biblioteca.controller;

import com.google.common.base.Optional;
import com.twu.biblioteca.exception.AccessDeniedException;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.view.MainFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;

import static com.twu.biblioteca.model.Movie.toMovie;

public class MovieReturnController extends MovieController{
    public MovieReturnController(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected void operate(Movie movie) throws AccessDeniedException {
        app.returned(movie);
        mainFrame.refresh(toDefaultTableModel(app.showMovies()), toDefaultTableModel(app.showCheckedoutMovies()));
    }

    @Override
    protected Optional<Movie> getModel(MouseEvent event) {
        Object[] model = mainFrame.getReturnedModel(event);
        return Optional.of(toMovie(model));
    }
    @Override
    protected boolean notVerify() {
        return JOptionPane.showConfirmDialog(mainFrame,
                "Do you want to return this movie?",
                "Confirm",
                JOptionPane.CANCEL_OPTION) != 0;
    }

    @Override
    public void init() {
        mainFrame.initReturnedPane(this);
    }
}
