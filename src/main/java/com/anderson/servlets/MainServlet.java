package com.anderson.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    private static final String url = "jdbc:postgresql://localhost:5432/students";
    public static final String username = "postgres";
    public static final String password = "123ASD123asd";

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static CallableStatement callableStatement;

    @Override
    public void init() throws ServletException {
        super.init();
        log("Method init");
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().write("Method service enter\n");
        super.service(req, resp);
//        resp.getWriter().write("Method service exit\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Method doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int score = Integer.parseInt(req.getParameter("score"));

//        try {
//            preparedStatement.setString(1, name);
//            preparedStatement.setInt(2, score);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        resp.getWriter().write("Student " + name + " (" + score + ") successfully added!\n\nOTHERS STUDENTS:");

        try {
            connect();
            resp.getWriter().write("++++++");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                resp.getWriter().write("id: " + rs.getInt("id") + ", name: " + rs.getString("name") + ", score: " + rs.getInt("score") + "\n");
            }
        } catch (SQLException e) {
        }
    }

    @Override
    public void destroy() {
        log("Method destroy");
        disconnect();
    }

    private static void loadDriver() throws SQLException {
        System.out.println("Loading driver...");
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Cannot find the driver in the classpath!", e);
        }
    }

    private static void connect() throws SQLException{
        loadDriver();

        System.out.println("Connecting database...");

        try {
            connection = DriverManager.getConnection(url, username, password);

            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
//            callableStatement = connection.prepareCall("{call editScore(?,?)}");

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

            System.out.println("Database successfully connected!");
        } catch (SQLException e) {
            throw new SQLException("Cannot connect the database!", e);
        }
    }

    public static void disconnect() {
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Database successfully disconnected!");
            }
        }
    }
}
