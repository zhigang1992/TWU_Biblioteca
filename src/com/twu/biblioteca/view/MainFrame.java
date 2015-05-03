package com.twu.biblioteca.view;

import com.twu.biblioteca.controller.MainController;
import com.twu.biblioteca.controller.ReturnController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;

import static java.lang.String.format;

public class MainFrame extends TopFrame {

    private JScrollPane scrollPane;
    private JScrollPane returnedScrollPane;
    private JTable table;
    private JTable returnedTable;
    private JMenuBar bar;

    public MainFrame(String name) throws HeadlessException {
        super(name);
        init();
        this.setVisible(true);
    }

    public void init() {
        bar = new JMenuBar();
        MainMenu mainMenu = new MainMenu("options");
        JMenu about = new JMenu("about");
        about.setEnabled(false);
        about.add(new JMenuItem("Me"));
        bar.add(mainMenu);
        bar.add(about);
        mainMenu.setFrame(this);
        setJMenuBar(bar);
        getContentPane().setLayout(new GridLayout(2, 1));
    }

    public JMenuBar getBar() {
        return bar;
    }

    public void initCheckoutPane(DefaultTableModel model, MainController controller) {
        table = new JTable(model);
        table.addMouseListener(controller);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Commodities"));
        scrollPane.setVisible(true);
        getContentPane().add(scrollPane, BorderLayout.NORTH);
    }

    public void initReturnedPane(MainController controller) {
        returnedTable = new JTable();
        returnedTable.addMouseListener(controller);
        returnedScrollPane = new JScrollPane(returnedTable);
        returnedScrollPane.setVisible(true);
        getContentPane().add(returnedScrollPane, BorderLayout.SOUTH);
    }

    public Object[] getCheckoutModel(MouseEvent event) {
        return selected(table.getSelectedRow(), table);
    }

    public Object[] getReturnedModel(MouseEvent event) {
        return selected(returnedTable.rowAtPoint(event.getPoint()), returnedTable);
    }

    private Object[] selected(int row, JTable table) {
        int count = table.getColumnCount();
        Object[] columnValues = new Object[count];
        for(int i=0; i<count; i++){
            columnValues[i] = table.getValueAt(row, i);
        }

        return columnValues;
    }

    public void refresh(DefaultTableModel checkoutModel, DefaultTableModel checkedoutModel) {
        table.setModel(checkoutModel);
        table.repaint();

        returnedScrollPane.setVisible(true);
        returnedTable.setModel(checkedoutModel);
        returnedTable.repaint();
    }

    public void setLoginInfo(String username) {
        if(returnedTable == null) return;
        returnedScrollPane.setBorder(BorderFactory.createTitledBorder(format("%s: Checkedout Commodities", username)));
    }
}
