package com.skytecgames.task.menu.action;

import com.skytecgames.task.service.TaskServiceImpl;
import com.skytecgames.task.service.TransactionServiceImpl;
import com.skytecgames.task.service.interfaces.TaskService;
import com.skytecgames.task.service.interfaces.TransactionService;

import java.util.Scanner;

public class CompleteTaskAction implements IAction {

    private TaskService taskService = TaskServiceImpl.getInstance();
    private TransactionService transactionService = TransactionServiceImpl.getInstance();

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
            if (taskService.completeTask(clanId, taskId)) {
                transactionService.updateTransactions(clanId, taskService.getTask(taskId).getPrice());
            }

        }
    }
}
