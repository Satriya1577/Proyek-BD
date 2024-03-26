package com.example.laundry;

import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlconnect {
    public static Connection conn = null;

    public static Connection ConnectDb() {
        String databaseName = "laundry";
        String databaseUser = "root";
        String databasePass = "12345";

        String url = "jdbc:mysql://localhost/"+databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, databaseUser, databasePass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
