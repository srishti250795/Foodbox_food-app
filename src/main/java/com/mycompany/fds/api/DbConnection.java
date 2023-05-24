package com.mycompany.fds.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
        public static final String USERNAME = "4EOsFfc8oy";
    public static final String PASSWORD = "w3CNWmWdVa";
    public static final String CONN = "jdbc:mysql://remotemysql.com:3306/4EOsFfc8oy";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN,USERNAME,PASSWORD);
    }
}
