package com.twu.biblioteca.view;

import javax.swing.*;
import java.awt.*;

public class TopFrame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    public TopFrame(String name) throws HeadlessException {
        super(name);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - WIDTH) / 2;
        int y = (screenSize.height - HEIGHT) / 2;
        setLocation(x, y);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
