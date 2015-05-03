package com.twu.biblioteca.controller;

import com.google.common.base.Optional;
import com.twu.biblioteca.exception.AccessDeniedException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.view.MainFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;

import static com.twu.biblioteca.model.Book.toBook;

public class CheckoutController extends BookController {

    public CheckoutController(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected void operate(Book book) throws AccessDeniedException {
        app.checkout(book);
        mainFrame.refresh(toDefaultTableModel(app.showBooks()), toDefaultTableModel(app.showCheckedoutBooks()));
    }

    @Override
    protected boolean notVerify() {
        return JOptionPane.showConfirmDialog(mainFrame,
                "Do you want to checkout this book?",
                "Confirm",
                JOptionPane.CANCEL_OPTION) != 0;
    }

    @Override
    protected Optional<Book> getModel(MouseEvent event) {
        Object[] model = mainFrame.getCheckoutModel(event);
        return Optional.of(toBook(model));
    }

    public void init() {
        mainFrame.initCheckoutPane(toDefaultTableModel(app.showBooks()), this);
    }
}
