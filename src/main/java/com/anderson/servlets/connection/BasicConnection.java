package com.anderson.servlets.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnection implements ConnectionPool {
    private String url = "jdbc:postgresql://localhost:5432/students";;
    private String user;
    private String password;

    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    public static BasicConnection create(
            String url, String user,
            String password) throws SQLException {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new BasicConnection(url, user, password, pool);
    }

    public BasicConnection(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    // standard getters
    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
//
//    private static final String url =
//    private static final String username = "postgres";
//    private static final String password = "securE@11here";
//
//    private static Connection connection;
//    private static Logger log = Logger.getLogger(Database.class.getName());;
//
//    private static void loadDriver() throws SQLException {
//        log.info("Loading driver...");
//        try {
//            Class.forName("org.postgresql.Driver");
//            log.info("Driver loaded!");
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("Cannot find the driver in the classpath!", e);
//        }
//    }
//
//    private static Connection connect() throws SQLException{
//        loadDriver();
//        log.info("Connecting database...");
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            log.info("Database successfully connected!");
//            return connection;
//        } catch (SQLException e) {
//            throw new SQLException("Cannot connect the database!", e);
//        }
//    }
//
//    public static void disconnect() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                log.info("Database successfully disconnected!");
//            }
//        }
//    }
//}


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