package com.twu.biblioteca.model;


import com.google.common.base.Objects;

import java.util.List;

public class User {
    private String name;
    private String password;
    private Role role;
    private String emailAddress;
    private String phoneNumber;
    private List<Book> books;
    private List<Movie> movies;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("Name", name)
                .add("Password", password)
                .add("Role", role)
                .add("Email Address", emailAddress)
                .add("PhoneNubmer", phoneNumber)
                .add("Books", books)
                .add("Movies", movies)
                .toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isAccessableFor(String password) {
        return this.password.equals(password);
    }

    public static class Builder {
        private String name;
        private String password;
        private String emailAddress;
        private String phoneNumber;
        private Role role = Role.VISITOR;
        private List<Book> books;
        private List<Movie> movies;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withBooks(List<Book> books) {
            this.books = books;
            return this;
        }

        public Builder withMovies(List<Movie> movies) {
            this.movies = movies;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = name;
            user.password = password;
            user.role = role;
            user.emailAddress = emailAddress;
            user.phoneNumber = phoneNumber;
            user.books = books;
            user.movies = movies;

            return user;
        }
    }
}
