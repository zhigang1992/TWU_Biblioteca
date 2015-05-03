package com.twu.biblioteca.view;

import com.twu.biblioteca.controller.LoginController;
import com.twu.biblioteca.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends TopFrame {

    private final JPasswordField passwordText;
    private final JTextField userText;
    private final MainFrame parent;

    public LoginFrame(MainFrame parent, String name) throws HeadlessException {
        super(name);
        this.parent = parent;
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new LoginController(this));

        JButton registerButton = new JButton("register");
        registerButton.setBounds(180, 80, 80, 25);
        registerButton.setEnabled(false);
        panel.add(registerButton);

        this.setContentPane(panel);
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }

    public JTextField getUserText() {
        return userText;
    }

    public void reset() {
        passwordText.setText("");
        userText.setText("");
    }

    public void showLogin(final User user) {
        this.parent.setLoginInfo(user.getName());
        this.parent.getBar().getMenu(0).getItem(2).setEnabled(false);
        JMenu menu = this.parent.getBar().getMenu(1);
        menu.setEnabled(true);
        menu.remove(menu.getItem(0));
        menu.add(new JMenuItem("Me")).addActionListener(listener(user));
    }

    private ActionListener listener(final User user) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(LoginFrame.this.parent,
                        String.format("name: %s\nemail adress: %s\nphone number: %s\n", user.getName(), user.getEmailAddress(), user.getPhoneNumber()),
                        "About Me",
                        JOptionPane.CANCEL_OPTION);
            }
        };
    }
}
