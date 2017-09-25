package com.ivashchenko.practice7;

import com.ivashchenko.practice7.menu.MenuHandler;

import java.sql.SQLException;

public class DBDemo {
    public static void main(String[] args) throws SQLException{
        MenuHandler menuHandler = new MenuHandler();
        menuHandler.handleMenu();
    }
}
