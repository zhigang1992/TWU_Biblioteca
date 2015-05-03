package com.twu.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends TopFrame {

    public WelcomeFrame(String name) throws HeadlessException {
        super(name);
        JButton ok = new JButton(name);
        ok.addActionListener(actionListener(this));
        this.add(ok);
        this.setVisible(true);
    }

    private ActionListener actionListener(final JFrame frame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MainFrame("Biblioteca App");
                frame.dispose();
            }
        };
    }
}
