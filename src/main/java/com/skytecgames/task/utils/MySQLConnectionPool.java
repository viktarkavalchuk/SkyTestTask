package com.skytecgames.task.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;

public class MySQLConnectionPool {

    private String driverName;
    private String databaseUrl;
    private String userName;
    private String password;
    private int maxPoolSize = 10;
    private int connNum = 0;

    private static final String SQL_VERIFYCONN = "select 1";

    Stack<Connection> freePool = new Stack<>();
    Set<Connection> occupiedPool = new HashSet<>();


    public MySQLConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("datasource.properties"));
            this.driverName = properties.getProperty("jdbc.driver");
            this.databaseUrl = properties.getProperty("jdbc.url");
            this.userName = properties.getProperty("jdbc.username");
            this.password = properties.getProperty("jdbc.password");
            this.maxPoolSize = 100;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
        }
        if (isFull()) {
            try {
                throw new SQLException("The connection pool is full.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        conn = getConnectionFromPool();

        if (conn == null) {
            try {
                conn = createNewConnectionForPool();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            conn = makeAvailable(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public synchronized void returnConnection(Connection conn)
            throws SQLException {
        if (conn == null) {
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(conn)) {
            throw new SQLException(
                    "The connection is returned already or it isn't for this pool");
        }
        freePool.push(conn);
    }

    private synchronized boolean isFull() {
        return ((freePool.size() == 0) && (connNum >= maxPoolSize));
    }

    private Connection createNewConnectionForPool() throws SQLException {
        Connection conn = createNewConnection();
        connNum++;
        occupiedPool.add(conn);
        return conn;
    }

    private Connection createNewConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(databaseUrl, userName, password);
        return conn;
    }

    private Connection getConnectionFromPool() {
        Connection conn = null;
        if (freePool.size() > 0) {
            conn = freePool.pop();
            occupiedPool.add(conn);
        }
        return conn;
    }

    private Connection makeAvailable(Connection conn) throws SQLException {
        if (isConnectionAvailable(conn)) {
            return conn;
        }

        occupiedPool.remove(conn);
        connNum--;
        conn.close();

        conn = createNewConnection();
        occupiedPool.add(conn);
        connNum++;
        return conn;
    }

    private boolean isConnectionAvailable(Connection conn) {
        try (Statement st = conn.createStatement()) {
            st.executeQuery(SQL_VERIFYCONN);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}