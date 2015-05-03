package com.twu.biblioteca.model;

import com.google.common.base.Function;
import com.google.common.base.Objects;

import static com.google.common.base.Objects.equal;

public class Book implements Commodity {
    private String name;
    private String authorName;
    private int publishedYear;
    private boolean isCheckedout = false;

    public static Builder builder() {
        return new Builder();
    }

    public static Book toBook(Object[] model) {
        return builder()
                .withName((String) model[0])
                .withAuthorName((String) model[1])
                .withPublishedYear((Integer) model[2])
                .build();
    }

    public static String[] bookHeader() {
        return new String[]{"Name", "Author", "Year"};
    }

    public static Function<Book, Object[]> toObjectArray() {
        return new Function<Book, Object[]>() {
            @Override
            public Object[] apply(Book book) {
                return new Object[]{book.getName(), book.getAuthorName(), book.getPublishedYear()};
            }
        };
    }

    public static class Builder {
        private String name;
        private String authorName;
        private int publishedYear;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Builder withPublishedYear(int publishedYear) {
            this.publishedYear = publishedYear;
            return this;
        }

        public Book build(){
            Book book = new Book();
            book.authorName = authorName;
            book.publishedYear = publishedYear;
            book.name = name;
            return book;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return equal(book.name, name)
                && equal(book.authorName, authorName)
                && equal(book.publishedYear, publishedYear);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, authorName, publishedYear);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("Name", name)
                .add("AuthorName", authorName)
                .add("PublishedYear", publishedYear)
                .add("IsCheckedout", isCheckedout)
                .toString();
    }

    public String getName() {
        return name;
    }

    public boolean isCheckedout() {
        return isCheckedout;
    }

    public void setCheckedout(boolean checkedout) {
        isCheckedout = checkedout;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
}
