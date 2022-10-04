package com.skytecgames.task.dao.interfaces;

import com.skytecgames.task.model.Transaction;

import java.util.ArrayList;

public interface TransactionDAO {
    boolean logTransaction(Long userId, Long taskId, long clanId, int goldBefore, int goldAfter, int reason);
    ArrayList<Transaction> getAllTransactions();
    ArrayList<Transaction> getTransactionsByClan(int clanId);
    ArrayList<Transaction> getTransactionsByUser(int userId);
    ArrayList<Transaction> getTransactionsByReason(int userId);
}
