package com.anderson.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Database {
    private static final String url = "jdbc:postgresql://localhost:5432/students";
    private static final String username = "postgres";
    private static final String password = "securE@11here";

    private static Connection connection;
    private static Logger log = Logger.getLogger(Database.class.getName());;

    private static void loadDriver() throws SQLException {
        log.info("Loading driver...");
        try {
            Class.forName("org.postgresql.Driver");
            log.info("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Cannot find the driver in the classpath!", e);
        }
    }

    private static Connection connect() throws SQLException{
        loadDriver();
        log.info("Connecting database...");
        try {
            connection = DriverManager.getConnection(url, username, password);
            log.info("Database successfully connected!");
            return connection;
        } catch (SQLException e) {
            throw new SQLException("Cannot connect the database!", e);
        }
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                log.info("Database successfully disconnected!");
            }
        }
    }
}


//          statement = connection.createStatement();
//          preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
//          callableStatement = connection.prepareCall("{call editScore(?,?)}");

            /**
             * create or replace procedure "EDITSCORE"
             * (eName varchar,
             * eScore int)
             * is
             * begin
             * UPDATE students SET score = eScore WHERE name = eName;
             * end;
             * /
             */