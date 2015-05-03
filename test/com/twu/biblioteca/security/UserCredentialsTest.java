package com.twu.biblioteca.security;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserCredentialsTest {

    private static final String RIGHT_USER_NAME = "abc-1234";
    private static final String RIGHT_PASSWORD = "1!2@3$";
    private static final String ERROR_PASSWORD = "error";
    private UserCredentials userCredentials;

    @Before
    public void setUp() throws Exception {
        userCredentials = UserCredentials.getInstance();
    }

    @Test
    public void should_success_to_login_with_right_username_and_password() throws Exception {
        boolean success = userCredentials.login(RIGHT_USER_NAME, RIGHT_PASSWORD);

        assertThat(success, is(true));
    }

    @Test
    public void should_fail_to_login_with_error_username_or_password() throws Exception {
        boolean success = userCredentials.login(RIGHT_USER_NAME, ERROR_PASSWORD);

        assertThat(success, is(false));
    }

    @Test
    public void should_success_for_access_allowed_role() throws Exception {
        userCredentials.login(RIGHT_USER_NAME, RIGHT_PASSWORD);
        boolean success = userCredentials.success();

        assertThat(success, is(true));
    }

    @Test
    public void should_failed_for_access_denied_role() throws Exception {
        userCredentials.login("admin-1234", "3$2@1!");
        boolean success = userCredentials.success();

        assertThat(success, is(false));
    }

    @Test
    public void should_force_to_refresh_user_credentials() throws Exception {
        userCredentials.login(RIGHT_USER_NAME, RIGHT_PASSWORD);
        userCredentials.refresh();

        assertThat(userCredentials.success(), is(false));
    }
}