package com.twu.biblioteca.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    @Test
    public void should_have_name_and_password() throws Exception {
        User user = User.builder()
                .withName("abc-1234")
                .withPassword("1!2@3$")
                .build();

        assertThat(user.getName(), is("abc-1234"));
        assertThat(user.getPassword(), is("1!2@3$"));
    }

    @Test
    public void should_have_default_role() throws Exception {
        User user = User.builder().build();

        assertThat(user.getRole(), is(Role.VISITOR));
    }

    @Test
    public void should_show_user_info() throws Exception {
        User user = User.builder().withName("abc-1234")
                .withPassword("1!2@3$")
                .withEmailAddress("codeeker@gmail.com")
                .withRole(Role.USER)
                .withPhoneNumber("18008049999")
                .build();

        assertThat(user.getEmailAddress(), is("codeeker@gmail.com"));
        assertThat(user.getPhoneNumber(), is("18008049999"));
    }
}
