package com.anderson.servlets;

import com.anderson.connection.DBConnection;
import com.anderson.dao.UserDAO;
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
//    public static void main(String[] args) {
//        List<UserModel> users = new ArrayList<>();
//        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * from users")) {
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Long id = rs.getLong("id");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                boolean status = rs.getBoolean("status");
//                users.add(new UserModel(name, age, status));
//            }
//            System.out.println(users.toString());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> users = UserDAO.selectAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = 0;
        try {
            age = Integer.parseInt(req.getParameter("age"));
            UserDAO.insert(new UserModel(name, age, true));
        } catch (Exception e) {
            req.setAttribute("error", "Incorrect age!");
        } finally {
            List<UserModel> users = UserDAO.selectAll();
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


