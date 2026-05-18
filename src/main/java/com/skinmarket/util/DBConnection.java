package com.skinmarket.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    static String dburl = "jdbc:postgresql://localhost:5432/skinmarket";
    static String userName = "postgres";
    static String password = "fulful89";

    public static Connection DbConnect(){
        try {
            Connection connection = DriverManager.getConnection(dburl, userName, password);
            System.out.println("Connected to database");
            return connection;
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
            return null;
        }
    }
}
