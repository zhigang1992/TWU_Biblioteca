package com.twu.biblioteca.controller;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.twu.biblioteca.BookApp;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.view.MainFrame;

import java.awt.event.MouseEvent;

import static com.twu.biblioteca.model.Book.*;

public class BookController extends MainController<Book>{
    protected BookApp app = new BookApp();

    public BookController(MainFrame mainFrame) {
        super(mainFrame);
        TABLE_HEADER = bookHeader();
    }

    @Override
    protected Optional<Book> getModel(MouseEvent event) {
        Object[] model = mainFrame.getCheckoutModel(event);

        return Optional.of(toBook(model));
    }

    @Override
    protected Function<Book, Object[]> toArray() {
        return toObjectArray();
    }
}
