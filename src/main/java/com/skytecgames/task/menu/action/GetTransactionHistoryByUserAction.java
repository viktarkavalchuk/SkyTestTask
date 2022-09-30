package com.skytecgames.task.menu.action;

import com.skytecgames.task.model.Transaction;
import com.skytecgames.task.service.TransactionServiceImpl;
import com.skytecgames.task.service.interfaces.TransactionService;

import java.util.ArrayList;
import java.util.Scanner;

public class GetTransactionHistoryByUserAction implements IAction {
    private TransactionService transactionService = TransactionServiceImpl.getInstance();
    @Override
    public void execute() throws Exception {
        System.out.println("Enter user ID");
        int userId = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            userId = scanner.nextInt();
        }
        ArrayList<Transaction> list = transactionService.getTransactionsByUser(userId);
        if (!list.isEmpty()) {
            list.stream()
                    .forEach(x -> System.out.println("Date of transfer: " + x.getDate()
                            + "; User ID: " + x.getUserId()
                            + "; Clan ID: " + x.getClanId()
                            + "; Gold: " + x.getGold() + ";"));
        } else {
            System.out.println("No entries by user");
        }
    }
}
