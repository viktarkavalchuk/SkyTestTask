package com.skytecgames.task.service;

import com.skytecgames.task.dao.TransactionDAOImpl;
import com.skytecgames.task.dao.interfaces.TransactionDAO;
import com.skytecgames.task.model.Transaction;

import java.util.ArrayList;

public class TransactionService {
    private static TransactionService instance;
    private TransactionDAO transactionDAO = TransactionDAOImpl.getInstance();

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    public boolean logTransaction(Long userId, Long taskId, long clanId, int goldBefore, int goldAfter, int reason) {
        return transactionDAO.logTransaction(userId, taskId, clanId, goldBefore, goldAfter, reason);
    }

    public ArrayList<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    public ArrayList<Transaction> getTransactionsByClan(int clanId) {
        return transactionDAO.getTransactionsByClan(clanId);
    }

    public ArrayList<Transaction> getTransactionsByUser(int userId) {
        return transactionDAO.getTransactionsByUser(userId);
    }

    public ArrayList<Transaction> getTransactionsByReason(int reasonId) {
        return transactionDAO.getTransactionsByReason(reasonId);
    }
}
