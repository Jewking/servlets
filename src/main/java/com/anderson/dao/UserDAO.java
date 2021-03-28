package com.anderson.dao;

import com.anderson.connection.DBConnection;
import com.anderson.model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String INSERT = "INSERT INTO users (name,age,status) VALUES (?,?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT name,age,status FROM users WHERE id=?";
    private static final String SELECT_USER_BY_NAME = "SELECT id,name,age,status FROM users WHERE name=?";
    private static final String SELECT_ALL = "SELECT * from users";
    private static final String DELETE_BY_ID = "DELETE from users where id=?";
    private static final String UPDATE_AGE_BY_ID = "UPDATE users set age=? where id =?";
    private static final String UPDATE_NAME_BY_ID = "UPDATE users set name=? where id =?";
    private static final String UPDATE_STATUS = "UPDATE users set status=? where id=?";
    private static final String DELETE_ALL = "DELETE from users";

    public static void insert(UserModel user) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
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

    public static UserModel select(Long userId) {
        UserModel user = new UserModel("null", 0, false);

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID);
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                boolean status = rs.getBoolean("status");
                user = new UserModel(userId, name, age, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updateStatus(Long userId, boolean status) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS);
            ps.setBoolean(1, status);
            ps.setLong(2, userId);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<UserModel> search(String sName) {
        List<UserModel> listUsers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_NAME);
            ps.setString(1, sName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                boolean status = rs.getBoolean("status");
                listUsers.add(new UserModel(id, name, age, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listUsers;
    }

    public static List<UserModel> selectAll() {
        List<UserModel> listUsers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
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

    public static void delete(Long userId) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateName(Long userId, String name) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_NAME_BY_ID);
            ps.setString(1, name);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAge(Long userId, int age) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_AGE_BY_ID);
            ps.setInt(1, age);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean wasDeletedById(Long userId) {
        boolean isRowDeleted = false;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
            ps.setLong(1, userId);
            isRowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRowDeleted;
    }

    public static void deleteAll() {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_ALL);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
