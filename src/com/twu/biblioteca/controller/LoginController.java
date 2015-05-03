package com.twu.biblioteca.controller;

import com.twu.biblioteca.security.UserCredentials;
import com.twu.biblioteca.view.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private final LoginFrame frame;

    public LoginController(LoginFrame loginFrame) {
        this.frame = loginFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] password = frame.getPasswordText().getPassword();
        String username = frame.getUserText().getText();
        UserCredentials credentials = UserCredentials.getInstance();
        boolean login = credentials.login(username, new String(password));
        if(login) {
            frame.showLogin(credentials.getCurrentUser());
            frame.dispose();
        } else {
            frame.reset();
        }
    }
}
