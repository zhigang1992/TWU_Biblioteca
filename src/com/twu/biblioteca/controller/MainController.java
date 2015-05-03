package com.twu.biblioteca.controller;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.twu.biblioteca.exception.AccessDeniedException;
import com.twu.biblioteca.model.Commodity;
import com.twu.biblioteca.view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.google.common.collect.FluentIterable.from;

public abstract class MainController<T extends Commodity> extends MouseAdapter {
    protected final MainFrame mainFrame;
    protected String[] TABLE_HEADER;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        if (event.getClickCount() == 2) {
            if (notVerify()) return;

            Optional<T> book = getModel(event);
            if (!book.isPresent()) return;

            try {
                operate(book.get());
            } catch (AccessDeniedException e) {
                JOptionPane.showMessageDialog(mainFrame, e.getMessage());
            }
        }
    }

    protected DefaultTableModel toDefaultTableModel(final ImmutableList<T> model) {
        return new DefaultTableModel(transform(model), TABLE_HEADER) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private Object[][] transform(ImmutableList<T> checkedoutCommodities) {
        ImmutableList<Object[]> objects = from(checkedoutCommodities).transform(toArray()).toList();

        int len = objects.size();
        Object[][] data = new Object[len][TABLE_HEADER.length];
        for (int i = 0; i < len; i++) {
            data[i] = objects.get(i);
        }

        return data;
    }

    protected Optional<T> getModel(MouseEvent event) {return null;};

    protected void operate(T t) throws AccessDeniedException {};

    protected abstract Function<T, Object[]> toArray();

    protected boolean notVerify() {
        return false;
    };

    public void init() {};
}
