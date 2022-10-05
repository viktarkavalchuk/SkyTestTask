package com.skytecgames.task.dao;

import com.skytecgames.task.dao.interfaces.TransactionDAO;
import com.skytecgames.task.model.Transaction;
import com.skytecgames.task.utils.MySQLConnectionPool;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class TransactionDAOImpl implements TransactionDAO {
    private static TransactionDAOImpl instance;

    public static TransactionDAOImpl getInstance(){
        if (instance == null) {
            instance = new TransactionDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean logTransaction(Long userId, Long taskId, long clanId, int goldBefore, int goldAfter, int reason) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        try (Connection conn = pool.getConnection()) {
            PreparedStatement updateStatement = conn
                    .prepareStatement("INSERT transactionhistory" +
                            " SET date = now(), userId = ?, taskId = ?, clanId = ?, " +
                            "goldBefore = ?, goldAfter = ?, reason = ?");
            if (userId != null) {
                updateStatement.setLong(1, userId);
            } else updateStatement.setNull(1,Types.BIGINT);
            if (taskId != null) {
                updateStatement.setLong(2, taskId);
            } else updateStatement.setNull(2,Types.BIGINT);
            updateStatement.setLong(3, clanId);
            updateStatement.setInt(4, goldBefore);
            updateStatement.setInt(5, goldAfter);
            updateStatement.setInt(6, reason);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Transaction> getAllTransactions() {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM transactionhistory t LEFT JOIN reason r on t.reason = r.idReason";
        Transaction transaction = null;
        ArrayList<Transaction> list = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    Timestamp date = resultSet.getTimestamp(2);
                    Long userId = resultSet.getLong(3);
                    Long taskId = resultSet.getLong(4);
                    int clanId = resultSet.getInt(5);
                    int goldBefore = resultSet.getInt(6);
                    int goldAfter = resultSet.getInt(7);
                    String reason = resultSet.getString(10);
                    transaction = new Transaction(id, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date),
                            userId, taskId, clanId, goldBefore, goldAfter, reason);
                    list.add(transaction);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<Transaction> getTransactionsByClan(int clanId) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM transactionhistory t " +
                "LEFT JOIN reason r on t.reason = r.idReason WHERE clanId = " + clanId;
        Transaction transaction = null;
        ArrayList<Transaction> list = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    Timestamp date = resultSet.getTimestamp(2);
                    Long userId = resultSet.getLong(3);
                    Long taskId = resultSet.getLong(4);
                    int thisClanId = resultSet.getInt(5);
                    int goldBefore = resultSet.getInt(6);
                    int goldAfter = resultSet.getInt(7);
                    String reason = resultSet.getString(10);
                    transaction = new Transaction(id, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date),
                            userId, taskId, thisClanId, goldBefore, goldAfter, reason);
                    list.add(transaction);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<Transaction> getTransactionsByUser(int userId) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM transactionhistory t " +
                "LEFT JOIN reason r on t.reason = r.idReason WHERE userId = " + userId;
        Transaction transaction = null;
        ArrayList<Transaction> list = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    Timestamp date = resultSet.getTimestamp(2);
                    Long thisUserId = resultSet.getLong(3);
                    Long taskId = resultSet.getLong(4);
                    int clanId = resultSet.getInt(5);
                    int goldBefore = resultSet.getInt(6);
                    int goldAfter = resultSet.getInt(7);
                    String reason = resultSet.getString(10);
                    transaction = new Transaction(id, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date),
                            thisUserId, taskId, clanId, goldBefore, goldAfter, reason);
                    list.add(transaction);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<Transaction> getTransactionsByReason(int reasonId) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM transactionhistory t " +
                "LEFT JOIN reason r on t.reason = r.idReason WHERE r.idReason = " + reasonId;
        Transaction transaction = null;
        ArrayList<Transaction> list = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    Timestamp date = resultSet.getTimestamp(2);
                    Long thisUserId = resultSet.getLong(3);
                    Long taskId = resultSet.getLong(4);
                    int clanId = resultSet.getInt(5);
                    int goldBefore = resultSet.getInt(6);
                    int goldAfter = resultSet.getInt(7);
                    String thisReason = resultSet.getString(10);
                    transaction = new Transaction(id, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date),
                            thisUserId, taskId, clanId, goldBefore, goldAfter, thisReason);
                    list.add(transaction);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
