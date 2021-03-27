package com.anderson.servlets;

import com.anderson.connection.DBConnection;
import com.anderson.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@WebServlet(urlPatterns = "/main")
public class UserServlet extends HttpServlet {


//
//    private static Statement statement;
//    private static PreparedStatement preparedStatement;
//    private static CallableStatement callableStatement;
//


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String SELECT_ALL = "SELECT * from users";

        List<UserModel> users = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("login");
                int age = rs.getInt("password");
                boolean status = rs.getBoolean("role");
                users.add(new UserModel(name, age, status));
            }
        } catch (SQLException e) {
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<UserModel> users = new ArrayList<UserModel> (Arrays.asList(
                    new UserModel("John First", 15, true),
                    new UserModel("Bob Second", 25, false),
                    new UserModel("Martin Third", 19, true)
                ));

        String name = req.getParameter("name");
        if (name.isEmpty()) {
            req.setAttribute("error", "Incorrect name!");
            req.setAttribute("users", users);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }
        int age = 0;
        try {
            age = Integer.parseInt(req.getParameter("age"));
            users.add(new UserModel(name, age, true));
        } catch (Exception e) {
            req.setAttribute("error", "Incorrect age!");
        } finally {
            req.setAttribute("users", users);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}

//        String name = req.getParameter("name");
//        int score = Integer.parseInt(req.getParameter("score"));

//        try {
//            preparedStatement.setString(1, name);
//            preparedStatement.setInt(2, score);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        resp.getWriter().write("Student " + name + " (" + score + ") successfully added!\n\nOTHERS STUDENTS:");

//        try (Connection con = connect()){
//            connect();
//            resp.getWriter().write("++++++");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ResultSet rs = statement.executeQuery("SELECT * FROM students");
//            while (rs.next()) {
//                resp.getWriter().write("id: " + rs.getInt("id") + ", name: " + rs.getString("name") + ", score: " + rs.getInt("score") + "\n");
//            }
//        } catch (SQLException e) {
//        }
//    }


