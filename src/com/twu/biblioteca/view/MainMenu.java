package com.twu.biblioteca.view;

import com.twu.biblioteca.controller.CheckoutController;
import com.twu.biblioteca.controller.MovieCheckoutController;
import com.twu.biblioteca.controller.MovieReturnController;
import com.twu.biblioteca.controller.ReturnController;
import com.twu.biblioteca.security.UserCredentials;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JMenu {
    private MainFrame frame;

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    public MainMenu(String name) {
        super(name);
        add(new JMenuItem("List Books")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.getContentPane().removeAll();
                CheckoutController checkoutController = new CheckoutController(frame);
                ReturnController returnController = new ReturnController(frame);
                checkoutController.init();
                returnController.init();

                frame.setVisible(true);
            }
        });
        add(new JMenuItem("List Movies")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.getContentPane().removeAll();
                MovieCheckoutController checkoutController = new MovieCheckoutController(frame);
                MovieReturnController returnController = new MovieReturnController(frame);
                checkoutController.init();
                returnController.init();
                frame.setVisible(true);
            }
        });

        add(new JMenuItem("Login")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(frame, "Login");
            }
        });

        add(new JMenuItem("Logout")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getBar().getMenu(1).setEnabled(false);
                frame.getBar().getMenu(0).getItem(2).setEnabled(true);
                UserCredentials.getInstance().refresh();
            }
        });

        add(new JMenuItem("Quit")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = JOptionPane.showConfirmDialog(null, "Do you want to leave BookApp?", "exit", JOptionPane.CANCEL_OPTION);
                if (i == 0) {
                    frame.dispose();
                }
            }
        });
    }
}
