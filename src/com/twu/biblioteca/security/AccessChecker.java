package com.twu.biblioteca.security;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.twu.biblioteca.model.Role;
import com.twu.biblioteca.model.User;

import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

public class AccessChecker {

    private Map<String, User> allowedAccessor = Maps.newHashMap();

    public AccessChecker() {
        User user = User.builder().withName("abc-1234")
                .withPassword("1!2@3$")
                .withEmailAddress("codeeker@gmail.com")
                .withRole(Role.USER)
                .withPhoneNumber("18008049999")
                .build();

        User admin = User.builder().withName("admin-1234")
                .withPassword("3$2@1!")
                .withEmailAddress("scott@gmail.com")
                .withRole(Role.ADMIN)
                .withPhoneNumber("18980123100")
                .build();

        allowedAccessor.put("abc-1234", user);
        allowedAccessor.put("admin-1234", admin);
    }

    public Optional<User> check(String userName, String password) {
        if(isNullOrEmpty(userName) || isNullOrEmpty(password)) {
            return Optional.absent();
        }

        User user = allowedAccessor.get(userName);
        if(user == null) {
            return Optional.absent();
        }

        if(user.isAccessableFor(password)) {
            return Optional.of(user);
        }
        return Optional.absent();
    }
}
