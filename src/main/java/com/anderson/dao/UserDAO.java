//package com.anderson.dao;
//
//import com.anderson.connection.DBConnection;
//import com.anderson.model.UserModel;
//
//import java.sql.*;
//
//public class UserDAO {
//    private static final String INSERT = "INSERT INTO users (name,age,status) VALUES (?,?,?)";
//    private static final String SELECT_USER_BY_ID = "SELECT name,age,status FROM users WHERE id=?";
//    private static final String SELECT_ALL = "SELECT * from users";
//    private static final String DELETE_BY_ID = "DELETE from users where id=?";
//    private static final String UPDATE_BY_ID = "UPDATE users set name=?, age=?, status=? where id =?";
//    private static final String DELETE_ALL = "DELETE from users";
//
//    public static void insert(UserModel user) {
//        try (Connection connection = DBConnection.getConnection();
//             PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
//            connection.setAutoCommit(false);
//            try {
//                ps.setString(1, user.getName());
//                ps.setInt(2, user.getAge());
//                ps.setBoolean(3, user.getStatus());
//                ps.executeUpdate();
//
//                final ResultSet rs = ps.getGeneratedKeys();
//                rs.next();
//                user.setId(rs.getLong("user_table_id"));
//                rs.close();
//
//                connection.commit();
//            } catch (Exception e) {
//                connection.rollback();
//                throw new RuntimeException(e);
//
//            }
//
//        } catch (SQLException | SQLException exception) {
//            printSQLException(exception);
//        }
//    }
//
//    public UserTableModel select(int userTableId) {
//        UserTableModel userTableModel = null;
//        try (Connection connection = createConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID)) {
//            ps.setInt(1, userTableId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String login = rs.getString("login");
//                String password = rs.getString("password");
//                String role = rs.getString("role");
//                userTableModel = new UserTableModel(userTableId, login, password, role);
//            }
//
//
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return userTableModel;
//    }
//
//    public List<UserTableModel> selectAll() {
//        List<UserTableModel> listUsers = new ArrayList<>();
//        try (Connection connection = createConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int user_table_id = rs.getInt("user_table_id");
//                String login = rs.getString("login");
//                String password = rs.getString("password");
//                String role = rs.getString("role");
//                listUsers.add(new UserTableModel(user_table_id, login, password, role));
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//
//
//        return listUsers;
//    }
//
//    public boolean isDeletedById(int userTableId) {
//        boolean isRowDeleted = false;
//        try (Connection connection = createConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
//            ps.setInt(1, userTableId);
//            isRowDeleted = ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return isRowDeleted;
//    }
//
//    public boolean isUpdatedById(UserTableModel userTableModel) {
//        boolean isRowUpdated = false;
//        try (Connection connection = createConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_BY_ID)) {
//            ps.setString(1, userTableModel.getLogin());
//            ps.setString(2, userTableModel.getPassword());
//            ps.setString(3, userTableModel.getRole());
//            ps.setInt(4, userTableModel.getUserTableId());
//            isRowUpdated = ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return isRowUpdated;
//    }
//
//    public void deleteAll() {
//        try (Connection connection = createConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_ALL)) {
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//    }
//}
