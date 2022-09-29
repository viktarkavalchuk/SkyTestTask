package com.skytecgames.task.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MySQLConnectionPool {
    private final String driverName = "com.mysql.cj.jdbc.Driver";
//    private final String connectionString = "jdbc:mysql://localhost:3306/hotel";
//    private final String login = "root";
//    private final String password = "root";

    private String databaseUrl;
    private String userName;
    private String password;
    private int maxPoolSize = 10;
    private int connNum = 0;

    private static final String SQL_VERIFYCONN = "select 1";

    Stack<Connection> freePool = new Stack<>();
    Set<Connection> occupiedPool = new HashSet<>();


    public MySQLConnectionPool() {
        this.databaseUrl = "jdbc:mysql://localhost:3306/skytecgames";
        this.userName = "root";
        this.password = "root";
        this.maxPoolSize = 100;
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

//    // Just an Example
//    public static void main(String[] args) throws SQLException {
//        Connection conn = null;
//        MySQLConnectionPool pool = new MySQLConnectionPool();
//        try {
//            conn = pool.getConnection();
//            try (Statement statement = conn.createStatement())
//            {
//                ResultSet res = statement.executeQuery("show tables");
//                System.out.println("There are below tables:");
//                while (res.next()) {
//                    String tblName = res.getString(1);
//                    System.out.println(tblName);
//                }
//            }
//        }
//        finally {
//            if (conn != null) {
//                pool.returnConnection(conn);
//            }
//        }
//    }

}