package com.skytecgames.task.menu.action;

import com.skytecgames.task.service.ClanService;
import com.skytecgames.task.service.TaskService;
import com.skytecgames.task.service.TransactionService;

import java.util.Scanner;

public class CompleteTaskAction implements IAction {

    private ClanService clanService = ClanService.getInstance();
    private TaskService taskService = TaskService.getInstance();
    private TransactionService transactionService = TransactionService.getInstance();

    @Override
    public void execute() {
        System.out.println("Enter clan ID");
        int clanId = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            clanId = scanner.nextInt();
        }
        System.out.println("Enter quest number");
        int taskId = 0;
        if (scanner.hasNextInt()) {
            taskId = scanner.nextInt();
            int goldBefore = clanService.getClan(clanId).getGold();
            if (taskService.completeTask(clanId, taskId)) {
                transactionService.logTransaction(null, (long) taskId, clanId, goldBefore,
                        clanService.getClan(clanId).getGold(),2);
            }
        }
    }
}
