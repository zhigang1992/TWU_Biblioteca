package com.twu.biblioteca;


import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.twu.biblioteca.exception.AccessDeniedException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.security.UserCredentials;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class BookAppTest {

    private BookApp app;

    @Before
    public void setUp() throws Exception {
        UserCredentials.getInstance().login("abc-1234", "1!2@3$");
        app = new BookApp();
    }

    @Test
    public void should_show_a_list_of_library_books() throws Exception {
        ImmutableList<Book> books = app.showBooks();

        assertThat(books.size(), greaterThan(0));
    }

    @Test
    public void should_checkout_a_book() throws Exception {
        Optional<Book> book = checkout("Pride and Prejudice", 1983, "Jane Austen");

        assertThat(book.isPresent(), is(true));
    }

    private Optional<Book> checkout(String name, int publishedYear, String authorName) throws AccessDeniedException {
        return app.checkout(Book.builder()
                .withName(name)
                .withPublishedYear(publishedYear)
                .withAuthorName(authorName).build());
    }

    @Test
    public void should_not_appear_in_library_books_for_having_checked_out() throws Exception {
        checkout("Pride and Prejudice", 1983, "Jane Austen");
        List<Book> books = app.showBooks();

        assertThat(books, not(hasItem(buildBook("Pride and Prejudice", "Jane Austen", 1983))));
    }

    private Book buildBook(String name, String authorName, int year) {
        return Book.builder()
                .withName(name)
                .withPublishedYear(year)
                .withAuthorName(authorName)
                .build();
    }

    @Test
    public void should_return_a_book() throws Exception {
        checkout("Pride and Prejudice", 1983, "Jane Austen");

        assertThat(app.returned(buildBook("Pride and Prejudice", "Jane Austen", 1983)), is(true));
    }

    @Test
    public void should_fail_to_return_a_book_for_unknown_book() throws Exception {
        assertThat(app.returned(Book.builder().withName("unknown").build()), is(false));
    }

    @Test
    public void should_fail_to_return_a_book_for_having_not_checked_out() throws Exception {
        assertThat(app.returned(buildBook("Pride and Prejudice", "Jane Austen", 1983)), is(false));
    }

    @Test
    public void should_show_checked_out_books() throws Exception {
        checkout("Pride and Prejudice", 1983, "Jane Austen");
        ImmutableList<Book> books = app.showCheckedoutBooks();

        assertThat(books, hasItem(buildBook("Pride and Prejudice", "Jane Austen", 1983)));
    }

    @Test(expected = AccessDeniedException.class)
    public void should_must_login_to_checkout_books() throws Exception {
        UserCredentials.getInstance().refresh();
        checkout("Pride and Prejudice", 1983, "Jane Austen");
    }
}
