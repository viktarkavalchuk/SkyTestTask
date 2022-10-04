package com.skytecgames.task.menu.action;

import com.skytecgames.task.service.ClanService;
import com.skytecgames.task.service.TransactionService;

import java.util.Scanner;

public class ClanPaymentAction implements IAction {

    private ClanService clanService = ClanService.getInstance();
    private TransactionService transactionService = TransactionService.getInstance();

    @Override
    public void execute() throws Exception {
        System.out.println("Enter clan ID");
        Scanner scanner = new Scanner(System.in);
        long clanId = 0;
        if (scanner.hasNextInt()) {
            clanId = scanner.nextInt();
        }
        int gold = 0;
        System.out.println("Enter payment amount");
        if (scanner.hasNextInt()) {
            gold = scanner.nextInt();
            try {
                int goldBerfore = clanService.getClan(clanId).getGold();
                if (clanService.paymentByClan(clanId, gold)){
                    transactionService.logTransaction(null, null, clanId, goldBerfore,
                            clanService.getClan(clanId).getGold(), 3);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("The entered value must be greater than zero");
            }
        }
    }
}
