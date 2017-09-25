package com.ivashchenko.practice7.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
    private static final String CONNECTION_PROPERTIES = "connection.properties";
    private static ConnectionHandler ourInstance = new ConnectionHandler();

    public static ConnectionHandler getInstance() {
        return ourInstance;
    }

    private ConnectionHandler() {
    }

    public ConnectionWrapper getConnection() throws SQLException{
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(CONNECTION_PROPERTIES);
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        String connectionUrl = properties.getProperty("connectionUrl");
        Connection connection = DriverManager.getConnection(connectionUrl, properties);
        ConnectionWrapper wrapper = new ConnectionWrapper(connection);
        return wrapper;
    }
}
