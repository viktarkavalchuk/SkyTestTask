package com.skytecgames.task.service.interfaces;

import com.skytecgames.task.model.Transaction;

import java.util.ArrayList;

public interface TransactionService {
    boolean updateTransactions(long clanId, int gold);
    boolean updateTransactions(long userId, long clanId, int gold);
    ArrayList<Transaction> getAllTransactions();
    ArrayList<Transaction> getTransactionsByClan(int clanId);
    ArrayList<Transaction> getTransactionsByUser(int userId);
}
