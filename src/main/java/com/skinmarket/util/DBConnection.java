package com.skinmarket.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/skinmarket";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "fulful89";

    public static Connection DbConnect() {
        try {
            // Загружаем драйвер
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Connected to database successfully");
            return connection;
        } catch (ClassNotFoundException e) {
            System.err.println("!!! PostgreSQL Driver not found !!!");
            e.printStackTrace();
            throw new RuntimeException("Driver not found", e);
        } catch (SQLException e) {
            System.err.println("!!! Database connection failed: " + e.getMessage());
            System.err.println("!!! Error code: " + e.getErrorCode());
            e.printStackTrace();
            throw new RuntimeException("Cannot connect to DB", e);
        }
    }
}