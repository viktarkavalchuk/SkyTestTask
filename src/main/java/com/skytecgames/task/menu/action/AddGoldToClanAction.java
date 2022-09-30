package com.skytecgames.task.menu.action;

import com.skytecgames.task.service.TransactionServiceImpl;
import com.skytecgames.task.service.UserAddGoldService;
import com.skytecgames.task.service.interfaces.TransactionService;

import java.util.Scanner;

public class AddGoldToClanAction implements IAction{

    private UserAddGoldService goldService = UserAddGoldService.getInstance();
    private TransactionService transactionService = TransactionServiceImpl.getInstance();
    @Override
    public void execute() {
        System.out.println("Enter user ID");
        long userId = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            userId = scanner.nextInt();
        }
        System.out.println("Enter clan ID");
        long clanId = 0;
        if (scanner.hasNextInt()) {
            clanId = scanner.nextInt();
        }
        int gold = 0;
        System.out.println("Enter amount of gold");
        if (scanner.hasNextInt()) {
            gold = scanner.nextInt();
            try {
                if (goldService.addGoldToClan(userId, clanId, gold)){
                    transactionService.updateTransactions(userId, clanId, gold);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("The entered value must be greater than zero");
            }
        }
    }
}
