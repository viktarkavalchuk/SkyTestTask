package com.skytecgames.task.menu.action;

import com.skytecgames.task.service.TransactionServiceImpl;
import com.skytecgames.task.service.interfaces.TransactionService;

public class GetTransactionHistoryAction implements IAction{

    private TransactionService transactionService = TransactionServiceImpl.getInstance();

    @Override
    public void execute() throws Exception {
        transactionService.getAllTransactions().stream()
                .forEach(x-> System.out.println("Date of transfer: " + x.getDate()
                        + "; User ID: " + x.getUserId()
                        + "; Clan ID: " + x.getClanId()
                        + "; Gold: " + x.getGold() + ";"));
    }
}
