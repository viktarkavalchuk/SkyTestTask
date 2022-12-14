package com.skytecgames.task.menu.action;

import com.skytecgames.task.service.ClanService;
import com.skytecgames.task.service.TransactionService;
import com.skytecgames.task.service.UserService;

import java.util.Scanner;

public class AddGoldToClanAction implements IAction{

    private ClanService clanService = ClanService.getInstance();
    private UserService userService = UserService.getInstance();
    private TransactionService transactionService = TransactionService.getInstance();

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
                int goldBerfore = clanService.getClan(clanId).getGold();
                if (userService.addGoldToClan(userId, clanId, gold)){
                    transactionService.logTransaction(userId, null, clanId, goldBerfore,
                            clanService.getClan(clanId).getGold(), 1);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("The entered value must be greater than zero");
            }
        }
    }
}
