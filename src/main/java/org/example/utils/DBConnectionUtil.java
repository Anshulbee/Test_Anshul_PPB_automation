package org.example.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            Properties props = new Properties();
            InputStream input
                    =
                    DBConnectionUtil.class
                            .getClassLoader()
                            .getResourceAsStream("config/api.properties");
            if (input == null) {
                throw new RuntimeException("api.properties not found");
            }
            props.load(input);
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Opening DB connection...");
            System.out.println("Connected to DB: cbsuat");
        } catch (Exception e) {
            throw new RuntimeException("X DB Connection failed", e);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}

