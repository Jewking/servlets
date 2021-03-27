package com.anderson.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DBConnection {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("org.postgresql.Driver");
//        config.setJdbcUrl("jdbc:postgresql:///postgres?cloudSqlInstance=learned-dahlia-308914:europe-north1:anderson&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=postgres&password=123123&useSSL=false");
        config.setJdbcUrl("jdbc:postgresql://192.168.0.106:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("123123");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
//        config.addDataSourceProperty("cloudSqlInstance", "learned-dahlia-308914:europe-north1:anderson");
//        config.addDataSourceProperty("ipTypes", "PUBLIC,PRIVATE");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBConnection(){}
}
