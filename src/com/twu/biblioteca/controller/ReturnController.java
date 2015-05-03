package com.twu.biblioteca.controller;

import com.google.common.base.Optional;
import com.twu.biblioteca.exception.AccessDeniedException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.view.MainFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;

import static com.twu.biblioteca.model.Book.toBook;
import static com.twu.biblioteca.model.Movie.toMovie;

public class ReturnController extends BookController {

    public ReturnController(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected void operate(Book book) throws AccessDeniedException {
        app.returned(book);
        mainFrame.refresh(toDefaultTableModel(app.showBooks()), toDefaultTableModel(app.showCheckedoutBooks()));
    }

    @Override
    protected boolean notVerify() {
        return JOptionPane.showConfirmDialog(mainFrame,
                "Do you want to return this book?",
                "Confirm",
                JOptionPane.CANCEL_OPTION) != 0;
    }

    @Override
    protected Optional<Book> getModel(MouseEvent event) {
        Object[] model = mainFrame.getReturnedModel(event);
        return Optional.of(toBook(model));
    }

    @Override
    public void init() {
        mainFrame.initReturnedPane(this);
    }
}