package com.twu.biblioteca.exception;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccessDeniedExceptionTest {
    @Test
    public void should_throws_some_usable_message() throws Exception {
        String message = "No Access Right";
        AccessDeniedException exception = new AccessDeniedException(message);

        assertThat(exception.getMessage(), is(message));
    }
}
