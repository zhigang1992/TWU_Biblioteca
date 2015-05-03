package com.twu.biblioteca;

import com.twu.biblioteca.view.MainMenu;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


public class MainMenuTest {

    private MainMenu mainMenu;

    @Before
    public void setUp() throws Exception {
        mainMenu = new MainMenu("BibliotecaApp");
    }

    @Test
    public void should_have_some_options() throws Exception {
        MainMenu mainMenu = new MainMenu("BibliotecaApp");
        int itemCount = mainMenu.getItemCount();

        assertThat(itemCount, greaterThan(0));
    }

    @Test
    public void should_show_list_books_option() throws Exception {
        JMenuItem item = mainMenu.getItem(0);

        assertThat(item.getText(), is("List Books"));
    }

    @Test
    public void should_have_login_option() throws Exception {
        JMenuItem item = mainMenu.getItem(2);

        assertThat(item.getText(), is("Login"));
    }

    @Test
    public void should_have_quit_option() throws Exception {
        JMenuItem item = mainMenu.getItem(2);

        assertThat(item.getText(), is("Quit"));
    }
}
