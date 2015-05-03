package com.twu.biblioteca.security;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.twu.biblioteca.model.Role;
import com.twu.biblioteca.model.User;

import static com.google.common.collect.ImmutableList.of;

public class UserCredentials {

    private final AccessChecker accessChecker = new AccessChecker();
    private final ImmutableList<Role> authorizedRoles = of(Role.USER);
    private final static UserCredentials instance = new UserCredentials();
    private User currentUser = null;

    private UserCredentials() {
    }

    private boolean authorized = false;

    public static UserCredentials getInstance() {
        return instance;
    }

    public boolean login(String username, String password) {
        Optional<User> aUser = accessChecker.check(username, password);
        if(!aUser.isPresent()) return false;

        authorize(aUser.get()); // authorize to checkout or return something.

        currentUser = aUser.get();
        return true;
    }

    private void authorize(User user) {
        if(getAuthorizedRoles().contains(user.getRole())){
            authorized = true;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ImmutableList<Role> getAuthorizedRoles() {
        return authorizedRoles;
    }

    public boolean success() {
        return authorized;
    }

    public void refresh() {
        currentUser = null;
        authorized = false;
    }
}
