package com.anderson.dao;

import com.anderson.connection.DBConnection;
import com.anderson.model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String INSERT = "INSERT INTO users (name,age,status) VALUES (?,?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT name,age,status FROM users WHERE id=?";
    private static final String SELECT_ALL = "SELECT * from users";
    private static final String DELETE_BY_ID = "DELETE from users where id=?";
    private static final String UPDATE_BY_ID = "UPDATE users set name=?, age=?, status=? where id =?";
    private static final String DELETE_ALL = "DELETE from users";

    public static void insert(UserModel user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            try {
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
                ps.setBoolean(3, user.getStatus());
                ps.executeUpdate();

                final ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                user.setId(rs.getLong("id"));
                rs.close();

                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserModel select(Long userid) {
        UserModel user = new UserModel("null", 0, false);
        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ps.setLong(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                boolean status = rs.getBoolean("status");
                user = new UserModel(userid, name, age, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<UserModel> selectAll() {
        List<UserModel> listUsers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                boolean status = rs.getBoolean("status");
                listUsers.add(new UserModel(userId, name, age, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listUsers;
    }

    public static boolean isDeletedById(Long userId) {
        boolean isRowDeleted = false;
        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
            ps.setLong(1, userId);
            isRowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRowDeleted;
    }

//    public boolean isUpdatedById(UserModel userTableModel) {
//        boolean isRowUpdated = false;
//        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_BY_ID)) {
//            ps.setString(1, userTableModel.getName());
//            ps.setString(2, userTableModel.getAge());
//            ps.setString(3, userTableModel.getStatus());
//            ps.setInt(4, userTableModel.getUserTableId());
//            isRowUpdated = ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return isRowUpdated;
//    }

    public static void deleteAll() {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_ALL)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
