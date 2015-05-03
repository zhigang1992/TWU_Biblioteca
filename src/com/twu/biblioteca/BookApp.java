package com.twu.biblioteca;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.twu.biblioteca.model.Book;

public class BookApp extends Library<Book> {

    public BookApp() {
        commodities = Lists.newArrayList();
        commodities.add(buildBook("Fate Night", "kylefang", 2014));
        commodities.add(buildBook("hello world", "Zhigang", 2014));
        commodities.add(buildBook("Pride and Prejudice", "Jane Austen", 1983));
    }

    private Book buildBook(String name, String authorName, int publishedYear) {
        return Book.builder()
                .withName(name)
                .withAuthorName(authorName)
                .withPublishedYear(publishedYear)
                .build();
    }

    public ImmutableList<Book> showBooks() {
        return notCheckedoutCommodities();
    }

    public ImmutableList<Book> showCheckedoutBooks() {
        return checkedoutCommodities();
    }
}
