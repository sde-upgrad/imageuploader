package com.upgrad.ImageHoster.common;

import java.sql.*;

public class JDBCConnector {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/im?user=postgres&password=123";


    private Connection connection = null;
    private Statement statement = null;

    private JDBCConnector() {
        try {
            Class.forName(DB_DRIVER);

            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }
    }

    private static JDBCConnector jdbcConnector = new JDBCConnector();

    public static JDBCConnector getInstant() {
        return jdbcConnector;
    }

    public ResultSet executeQuery(final String query) {

        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("problem executing query");

            e.printStackTrace();
        }
        return null;
    }

    public void execute(final String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("problem executing");

            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        JDBCConnector jdbcConnector = JDBCConnector.getInstant();


       // jdbcConnector.execute("create table image(id SERIAL PRIMARY KEY, title varchar, description varchar, imageFile varchar, numView varchar,  uploadDate timestamp)");
    }
}
