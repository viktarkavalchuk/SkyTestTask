package com.skytecgames.task.service;

import com.skytecgames.task.model.Transaction;
import com.skytecgames.task.service.interfaces.TransactionService;
import com.skytecgames.task.utils.MySQLConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;


public class TransactionServiceImpl implements TransactionService {
    private static TransactionServiceImpl instance;

    public static TransactionServiceImpl getInstance(){
        if (instance == null) {
            instance = new TransactionServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean updateTransactions(long clanId, int gold) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        try (Connection conn = pool.getConnection()) {
            PreparedStatement updateStatement = conn
                    .prepareStatement("INSERT transactionhistory" +
                            " SET date = ?, clan = ?, goldInTransaction = ?");
            updateStatement.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
            updateStatement.setLong(2, clanId);
            updateStatement.setInt(3, gold);
            updateStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateTransactions(long userId, long clanId, int gold) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        try (Connection conn = pool.getConnection()) {
            PreparedStatement updateStatement = conn
                    .prepareStatement("INSERT transactionhistory" +
                            " SET date = ?, user = ?, clan = ?, goldInTransaction = ?");
            updateStatement.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
            updateStatement.setLong(2, userId);
            updateStatement.setLong(3, clanId);
            updateStatement.setInt(4, gold);
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
        String sql = "SELECT * FROM transactionhistory";
        Transaction transaction = null;
        ArrayList<Transaction> list = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    Date date = resultSet.getDate(2);
                    int userId = resultSet.getInt(3);
                    int clanId = resultSet.getInt(4);
                    int gold = resultSet.getInt(5);
                    transaction = new Transaction(id, date, userId, clanId, gold);
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
        String sql = "SELECT * FROM transactionhistory WHERE clan = " + clanId;
        Transaction transaction = null;
        ArrayList<Transaction> list = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    Date date = resultSet.getDate(2);
                    int userId = resultSet.getInt(3);
                    int thisClanId = resultSet.getInt(4);
                    int gold = resultSet.getInt(5);
                    transaction = new Transaction(id, date, userId, thisClanId, gold);
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
        String sql = "SELECT * FROM transactionhistory WHERE user = " + userId;
        Transaction transaction = null;
        ArrayList<Transaction> list = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    Date date = resultSet.getDate(2);
                    int thisUserId = resultSet.getInt(3);
                    int thisClanId = resultSet.getInt(4);
                    int gold = resultSet.getInt(5);
                    transaction = new Transaction(id, date, thisUserId, thisClanId, gold);
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
