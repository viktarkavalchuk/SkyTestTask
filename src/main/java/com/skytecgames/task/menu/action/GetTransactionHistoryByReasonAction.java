package com.skytecgames.task.menu.action;

import com.skytecgames.task.model.Transaction;
import com.skytecgames.task.service.TransactionService;

import java.util.ArrayList;
import java.util.Scanner;

public class GetTransactionHistoryByReasonAction implements IAction {
    private TransactionService transactionService = TransactionService.getInstance();
    @Override
    public void execute() throws Exception {
        System.out.println("Enter reason ID");
        int reasonId = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            reasonId = scanner.nextInt();
        }
        ArrayList<Transaction> list = transactionService.getTransactionsByReason(reasonId);
        if (!list.isEmpty()) {
            list.stream()
                    .forEach(x -> System.out.println("Date of transfer: " + x.getDate()
                            + "; User ID: " + x.getUserId()
                            + "; Task ID: " + x.getTaskId()
                            + "; Clan ID: " + x.getClanId()
                            + "; Gold before transaction: " + x.getGoldBefore()
                            + "; Gold after transaction: " + x.getGoldAfter()
                            + "; Reason: " + x.getReason() + ";"));
        } else {
            System.out.println("No entries by reason");
        }
    }
}
