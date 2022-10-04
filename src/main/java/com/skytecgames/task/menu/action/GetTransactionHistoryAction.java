package com.skytecgames.task.menu.action;

import com.skytecgames.task.service.TransactionService;

public class GetTransactionHistoryAction implements IAction{

    private TransactionService transactionService = TransactionService.getInstance();

    @Override
    public void execute() throws Exception {
        transactionService.getAllTransactions().stream()
                .forEach(x-> System.out.println("Date of transfer: " + x.getDate()
                        + "; User ID: " + x.getUserId()
                        + "; Task ID: " + x.getTaskId()
                        + "; Clan ID: " + x.getClanId()
                        + "; Gold before transaction: " + x.getGoldBefore()
                        + "; Gold after transaction: " + x.getGoldAfter()
                        + "; Reason: " +  x.getReason() + ";"));
    }
}
