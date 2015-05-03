package com.twu.biblioteca.model;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BookTest {
    @Test
    public void should_book_include_author_and_year_published() throws Exception {
        Book book = Book.builder()
                .withAuthorName("codeeker")
                .withPublishedYear(2014)
                .build();

        assertThat(book.getAuthorName(), is("codeeker"));
        assertThat(book.getPublishedYear(), is(2014));
    }
}
