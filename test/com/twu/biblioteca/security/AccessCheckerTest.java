package com.twu.biblioteca.security;

import com.google.common.base.Optional;
import com.twu.biblioteca.model.Role;
import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccessCheckerTest {

    private static final String RIGHT_USER_NAME = "abc-1234";
    private static final String RIGHT_PASSWORD = "1!2@3$";
    private static final String ERROR_PASSWORD = "error";
    private AccessChecker accessChecker;

    @Before
    public void setUp() throws Exception {
        accessChecker = new AccessChecker();
    }

    @Test
    public void should_allow_right_user_name_and_password() throws Exception {
        Optional<User> rightUser = accessChecker.check(RIGHT_USER_NAME, RIGHT_PASSWORD);

        assertThat(rightUser.isPresent(), is(true));
        assertThat(rightUser.get().getRole(), is(Role.USER));
    }

    @Test
    public void should_denied_for_not_matched_user_name_and_password() throws Exception {
        Optional<User> user = accessChecker.check(RIGHT_USER_NAME, ERROR_PASSWORD);

        assertThat(user.isPresent(), is(false));
    }

    @Test
    public void should_denied_for_not_exists_user_name() throws Exception {
        Optional<User> user = accessChecker.check("whoami", RIGHT_PASSWORD);

        assertThat(user.isPresent(), is(false));
    }

    @Test
    public void should_denied_for_nullable_user_name_or_password() throws Exception {
        Optional<User> checkUsername = accessChecker.check(null, RIGHT_PASSWORD);
        Optional<User> checkPassword = accessChecker.check(RIGHT_USER_NAME, null);

        assertThat(checkUsername.isPresent() || checkPassword.isPresent(), is(false));
    }
}
